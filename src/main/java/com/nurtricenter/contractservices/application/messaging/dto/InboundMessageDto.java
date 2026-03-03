package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nurtricenter.contractservices.infrastructure.messaging.kafka.util.UuidDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundMessageDto<T> {

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
    private T payload;

    public boolean isProcessable(
            String inputService,
            String inputOperation,
            String inputVerb
    ) {
        return transactionId != null &&
                inputService.equals(service) &&
                inputOperation.equals(operation) &&
                inputVerb.equals(verb) &&
                payload != null;
    }

}
