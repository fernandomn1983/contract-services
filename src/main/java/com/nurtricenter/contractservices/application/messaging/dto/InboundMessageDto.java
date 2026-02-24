package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nurtricenter.contractservices.infrastructure.messaging.kafka.util.UuidDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.nurtricenter.contractservices.application.messaging.constant.InboundConstant.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundMessageDto {

    @JsonProperty("transactionId")
    @JsonDeserialize(using = UuidDeserializer.class)
    private UUID transactionId;

    @JsonProperty("service")
    private String service;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("verb")
    private String verb;

    @JsonProperty("payload")
    private InboundPayloadDto payload;

    public boolean isProcessable() {
        return transactionId != null &&
                SERVICE_INBOUND_VALUE.equals(service) &&
                OPERATION_INBOUND_VALUE.equals(operation) &&
                VERB_INBOUND_VALUE.equals(verb) &&
                payload != null &&
                payload.getPatientId() != null;
    }

}
