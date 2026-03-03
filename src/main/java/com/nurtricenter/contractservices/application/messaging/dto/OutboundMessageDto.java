package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nurtricenter.contractservices.application.messaging.constant.OutboundConstant;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OutboundMessageDto<T> {

    @JsonProperty("transactionId")
    private UUID transactionId;

    @JsonProperty("service")
    private String service;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("verb")
    private String verb;

    @JsonProperty("payload")
    private T payload;

    public static <T> OutboundMessageDto<T> createConfirmation(
            UUID transactionId,
            String operation,
            String verb,
            T payload
    ) {
        return OutboundMessageDto.<T>builder()
                .transactionId(transactionId)
                .service(OutboundConstant.PATIENT_SERVICE_OUTBOUND_TARGET_VALUE)
                .operation(operation)
                .verb(verb)
                .payload(payload)
                .build();
    }

}
