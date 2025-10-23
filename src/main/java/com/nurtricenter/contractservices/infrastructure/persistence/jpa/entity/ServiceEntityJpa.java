package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "service")
@Getter
@Setter
@ToString
public class ServiceEntityJpa {

    @Id
    @Column(name = "service_id")
    private UUID id;

    @Column(name = "service_description")
    private String description;

    @Column(name = "service_cost")
    private BigDecimal cost;

    @OneToMany(mappedBy = "serviceId")
    private List<ContractServiceEntityJpa> services;

}
