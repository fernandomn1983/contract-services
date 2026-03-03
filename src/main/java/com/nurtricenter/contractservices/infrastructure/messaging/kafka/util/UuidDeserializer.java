package com.nurtricenter.contractservices.infrastructure.messaging.kafka.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.nurtricenter.contractservices.infrastructure.messaging.kafka.constant.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class UuidDeserializer extends JsonDeserializer<UUID> {

    @Override
    public UUID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String uuidString = jsonParser.getValueAsString();
        try {
            return UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            log.error(KafkaConstant.ERROR_DESERIALIZING_UUID_VALUE_LOG, uuidString, e.getMessage());
            return null;
        }
    }

}
