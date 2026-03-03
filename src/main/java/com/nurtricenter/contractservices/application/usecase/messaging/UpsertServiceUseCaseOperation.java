package com.nurtricenter.contractservices.application.usecase.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurtricenter.contractservices.application.messaging.constant.InboundConstant;
import com.nurtricenter.contractservices.application.messaging.constant.OutboundConstant;
import com.nurtricenter.contractservices.application.messaging.dto.InboundServicePayloadDto;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundMessageDto;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundServicePayloadDto;
import com.nurtricenter.contractservices.application.messaging.repository.OutboxRepository;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.service.ServiceRepository;
import com.nurtricenter.contractservices.domain.valueobjects.Money;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.nurtricenter.contractservices.application.messaging.constant.UseCaseConstant.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class UpsertServiceUseCaseOperation extends Operation<InboundServicePayloadDto> {

    private final ServiceRepository serviceRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean isProcessable() {
        return this.message.isProcessable(
                InboundConstant.SERVICE_INBOUND_VALUE,
                InboundConstant.OPERATION_SYNC_SERVICE_VALUE,
                InboundConstant.VERB_INBOUND_VALUE
        );
    }

    @Transactional
    @Override
    public void execute() {
        InboundServicePayloadDto inboundServicePayloadDto = objectMapper.convertValue(message.getPayload(), InboundServicePayloadDto.class);
        try {
            log.info(PROCESSING_SERVICE_UPSERT_OPERATION_LOG, message.getTransactionId(), inboundServicePayloadDto.getServiceId());
            ServiceDomain serviceDomain = getServiceData(inboundServicePayloadDto);
            ServiceDomain serviceDomainResult = serviceRepository.upsertService(serviceDomain);

            OutboundMessageDto<OutboundServicePayloadDto> confirmationMessage = OutboundMessageDto.createConfirmation(
                    message.getTransactionId(),
                    OutboundConstant.SERVICE_OUTBOUND_OPERATION_VALUE,
                    OutboundConstant.CONFIRMING_VERB_VALUE,
                    OutboundServicePayloadDto.builder()
                            .serviceId(serviceDomainResult.getServiceId())
                            .build()
            );

            outboxRepository.publicInOutbox(confirmationMessage);
            log.info(SERVICE_UPSERT_OPERATION_PROCESSED_LOG, message.getTransactionId(), inboundServicePayloadDto.getServiceId());
        } catch (Exception e) {
            log.warn(ERROR_PROCESSING_SERVICE_UPSERT_OPERATION_LOG, message.getTransactionId(), inboundServicePayloadDto.getServiceId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ServiceDomain getServiceData(InboundServicePayloadDto payload) {
        ServiceDomain serviceDomain = new ServiceDomain();

        serviceDomain.setServiceId(payload.getServiceId());
        serviceDomain.setDescription(payload.getServiceDescription());
        serviceDomain.setPrice(new Money(payload.getServiceCost()));

        return serviceDomain;
    }

}
