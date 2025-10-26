package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contract_service")
@IdClass(ContractServiceId.class)
@Getter
@Setter
public class ContractServiceEntityJpa {

    @Id
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntityJpa serviceId;

    @Id
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private ContractEntityJpa contractId;

    @Column(name = "contract_service_quantity", nullable = false)
    private int quantity;

}
