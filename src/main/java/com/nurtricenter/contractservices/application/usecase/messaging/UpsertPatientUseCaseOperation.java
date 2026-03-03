package com.nurtricenter.contractservices.application.usecase.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurtricenter.contractservices.application.messaging.constant.InboundConstant;
import com.nurtricenter.contractservices.application.messaging.constant.OutboundConstant;
import com.nurtricenter.contractservices.application.messaging.dto.InboundPatientPayloadDto;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundMessageDto;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundPatientPayloadDto;
import com.nurtricenter.contractservices.application.messaging.repository.OutboxRepository;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.nurtricenter.contractservices.application.messaging.constant.UseCaseConstant.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class UpsertPatientUseCaseOperation extends Operation<InboundPatientPayloadDto> {

    private final PatientRepository patientRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean isProcessable() {
        return message.isProcessable(
                InboundConstant.SERVICE_INBOUND_VALUE,
                InboundConstant.OPERATION_SYNC_PATIENT_VALUE,
                InboundConstant.VERB_INBOUND_VALUE
        );
    }

    @Transactional
    @Override
    public void execute() {
        InboundPatientPayloadDto inboundPatientPayloadDto = objectMapper.convertValue(message.getPayload(), InboundPatientPayloadDto.class);
        try {
            log.info(PROCESSING_PATIENT_UPSERT_OPERATION_LOG, message.getTransactionId(), inboundPatientPayloadDto.getPatientId());
            PatientDomain patientDomain = getPatientData(inboundPatientPayloadDto);
            PatientDomain patientDomainResult = patientRepository.upsertPatient(patientDomain);

            OutboundMessageDto<OutboundPatientPayloadDto> confirmationMessage = OutboundMessageDto.createConfirmation(
                    message.getTransactionId(),
                    OutboundConstant.PATIENT_OPERATION_OUTBOUND_VALUE,
                    OutboundConstant.CONFIRMING_VERB_VALUE,
                    OutboundPatientPayloadDto.builder()
                            .patientId(UUID.fromString(patientDomainResult.getPatientId()))
                            .build()
            );

            outboxRepository.publicInOutbox(confirmationMessage);
            log.info(PATIENT_UPSERT_OPERATION_PROCESSED_LOG, message.getTransactionId(), inboundPatientPayloadDto.getPatientId());
        } catch (Exception e) {
            log.warn(ERROR_PROCESSING_PATIENT_UPSERT_OPERATION_LOG, message.getTransactionId(), inboundPatientPayloadDto.getPatientId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private PatientDomain getPatientData(InboundPatientPayloadDto payload) {
        PatientDomain patientDomain = new PatientDomain();

        patientDomain.setPatientId(payload.getPatientId().toString());
        patientDomain.setFirstName(payload.getPatientName());
        patientDomain.setLastName(payload.getPatientLastName());
        patientDomain.setIdDocument(payload.getPatientIdentityDocument());

        return patientDomain;
    }

}
