package com.nurtricenter.contractservices.infrastructure.messaging.kafka.inbound;

import com.nurtricenter.contractservices.application.messaging.dto.InboundMessageDto;
import com.nurtricenter.contractservices.application.usecase.messaging.UnknownMessageCloserOperation;
import com.nurtricenter.contractservices.application.usecase.messaging.UpsertPatientUseCaseOperation;
import com.nurtricenter.contractservices.application.usecase.messaging.UpsertServiceUseCaseOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.nurtricenter.contractservices.infrastructure.messaging.kafka.constant.KafkaConstant.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaInboundConsumer {

    private final UpsertPatientUseCaseOperation upsertPatientUseCaseOperation;
    private final UpsertServiceUseCaseOperation upsertServiceUseCaseOperation;
    private final UnknownMessageCloserOperation unknownMessageCloserOperation;

    @KafkaListener(
            topics = INBOUND_TOPIC,
            groupId = KAFKA_CONSUMER_GROUP_ID,
            containerFactory = LISTENER_CONTAINER_FACTORY
    )
    public void consume(@Payload InboundMessageDto message, Acknowledgment ack) {
        try {
            log.info(STARTING_PROCESS_MESSAGE_LOG, message.getTransactionId());

            upsertPatientUseCaseOperation.linkWith(upsertServiceUseCaseOperation, message);
            upsertServiceUseCaseOperation.linkWith(unknownMessageCloserOperation, message);
            unknownMessageCloserOperation.linkWith(null, message);

            upsertPatientUseCaseOperation.process();

            ack.acknowledge();
            log.info(MESSAGE_PROCESSING_FINISHED_LOG, message.getTransactionId());
        } catch (Exception e) {
            log.error(ERROR_PROCESSING_MESSAGE_LOG, message.getTransactionId(), e.getMessage());

            throw new RuntimeException(e);
        }
    }

}
