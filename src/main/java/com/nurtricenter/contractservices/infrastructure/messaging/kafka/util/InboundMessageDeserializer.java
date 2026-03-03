package com.nurtricenter.contractservices.infrastructure.messaging.kafka.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nurtricenter.contractservices.application.messaging.dto.InboundMessageDto;
import com.nurtricenter.contractservices.infrastructure.messaging.kafka.constant.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

@Slf4j
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
            log.error(KafkaConstant.ERROR_DESERIALIZING_INBOUND_MESSAGE_LOG, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
