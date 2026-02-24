package com.nurtricenter.contractservices.infrastructure.messaging.kafka.inbound;

import com.nurtricenter.contractservices.application.messaging.dto.InboundMessageDto;
import com.nurtricenter.contractservices.application.usecase.UpsertPatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.nurtricenter.contractservices.infrastructure.messaging.kafka.constant.KafkaConstant.*;

@Component
@RequiredArgsConstructor
public class InboundConsumer {

    private final UpsertPatientUseCase upsertPatientUseCase;

    @KafkaListener(
            topics = INBOUND_TOPIC,
            groupId = KAFKA_CONSUMER_GROUP_ID,
            containerFactory = LISTENER_CONTAINER_FACTORY
    )
    public void consume(@Payload InboundMessageDto message, Acknowledgment ack) {
        try {
            if (!message.isProcessable()) {
                return;
            }
            upsertPatientUseCase.execute(message);
            ack.acknowledge();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
