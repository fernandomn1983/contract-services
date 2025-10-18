package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "service")
@Getter
@Setter
public class ServiceEntityJpa {

    @Id
    @Column(name = "service_id")
    private UUID id;

    @Column(name = "service_description")
    private String description;

    @Column(name = "service_cost")
    private BigDecimal cost;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
