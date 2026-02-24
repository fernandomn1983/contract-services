package com.nurtricenter.contractservices.infrastructure.messaging.kafka.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nurtricenter.contractservices.application.messaging.dto.InboundMessageDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class InboundMessageDeserializer implements Deserializer<InboundMessageDto> {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public InboundMessageDto deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        try {
            String json = new String(data, StandardCharsets.UTF_8);

            return mapper.readValue(json, InboundMessageDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
