package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurtricenter.contractservices.application.messaging.constant.OutboundConstant;
import com.nurtricenter.contractservices.application.messaging.dto.OutboundMessageDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "aggregate_id", nullable = false)
    private String aggregateId;

    @Column(name = "aggregate_type", nullable = false)
    private String aggregateType;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "payload", columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private String payload;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "processed")
    private boolean processed;

    @Column(name = "topic")
    private String topic;

    public static OutboxEntityJpa create(OutboundMessageDto message, String topic) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return OutboxEntityJpa.builder()
                    .aggregateId(message.getTransactionId().toString())
                    .aggregateType(OutboundConstant.PATIENT_AGGREGATE_TYPE)
                    .eventType(message.getVerb())
                    .payload(mapper.writeValueAsString(message))
                    .createdAt(LocalDateTime.now())
                    .processed(false)
                    .topic(topic)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
