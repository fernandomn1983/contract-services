package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nurtricenter.contractservices.infrastructure.messaging.kafka.util.UuidDeserializer;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundMessageDto<T> {

    @JsonProperty("aggregateId")
    @JsonDeserialize(using = UuidDeserializer.class)
    private UUID transactionId;

    @JsonProperty("aggregateType")
    private String service;

    @JsonProperty("type")
    private String operation;

    @JsonProperty("verb")
    private String verb = "upsert";

    @JsonProperty("payload")
    private T payload;

    public boolean isProcessable(String inputService, String inputOperation, String inputVerb) {
        return transactionId != null
                && inputService.equals(service)
                && inputOperation.equals(operation)
                && inputVerb.equals(verb)
                && payload != null;
    }
}
