package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.nurtricenter.contractservices.application.messaging.constant.OutboundConstant.*;

@Data
@Builder
public class OutboundMessageDto {

    @JsonProperty("transactionId")
    private UUID transactionId;

    @JsonProperty("service")
    private String service;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("verb")
    private String verb;

    @JsonProperty("payload")
    private OutboundPayloadDto payload;

    public static OutboundMessageDto createConfirmation(UUID transactionId, UUID patientId) {
        return OutboundMessageDto.builder()
                .transactionId(transactionId)
                .service(PATIENT_SERVICE_OUTBOUND_TARGET_VALUE)
                .operation(PATIENT_OPERATION_OUTBOUND_VALUE)
                .verb(PATIENT_VERB_OUTBOUND_VALUE)
                .payload(OutboundPayloadDto.builder()
                        .patientId(patientId)
                        .build())
                .build();
    }

}
