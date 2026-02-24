package com.nurtricenter.contractservices.application.usecase;

import com.nurtricenter.contractservices.application.messaging.dto.InboundMessageDto;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundMessageDto;
import com.nurtricenter.contractservices.application.messaging.repository.OutboxRepository;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpsertPatientUseCase {

    private final PatientRepository patientRepository;
    private final OutboxRepository outboxRepository;

    @Transactional
    public void execute(InboundMessageDto message) {
        try {
            PatientDomain patientDomain = getPatientData(message);
            PatientDomain patientDomainResult = patientRepository.upsertPatient(patientDomain);

            OutboundMessageDto confirmationMessage = OutboundMessageDto.createConfirmation(
                    message.getTransactionId(),
                    UUID.fromString(patientDomainResult.getPatientId())
            );

            outboxRepository.publicInOutbox(confirmationMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PatientDomain getPatientData(InboundMessageDto message) {
        PatientDomain patientDomain = new PatientDomain();

        patientDomain.setPatientId(message.getPayload().getPatientId().toString());
        patientDomain.setFirstName(message.getPayload().getPatientName());
        patientDomain.setLastName(message.getPayload().getPatientLastName());
        patientDomain.setIdDocument(message.getPayload().getPatientIdentityDocument());

        return patientDomain;
    }

}
