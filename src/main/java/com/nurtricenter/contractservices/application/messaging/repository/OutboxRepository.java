package com.nurtricenter.contractservices.application.messaging.repository;

import com.nurtricenter.contractservices.application.messaging.constant.OutboundConstant;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundMessageDto;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.OutboxEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.OutboxRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OutboxRepository {

    private final OutboxRepositoryJpa outboxRepositoryJpa;

    @Value(OutboundConstant.OUTBOUND_TOPIC)
    private String outboundTopic;

    @Transactional
    public void publicInOutbox(OutboundMessageDto message) {
        OutboxEntityJpa outboxEntityJpa = OutboxEntityJpa.create(message, outboundTopic);
        outboxRepositoryJpa.save(outboxEntityJpa);
    }

}
