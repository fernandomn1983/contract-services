package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contract")
@Getter
@Setter
public class ContractEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Integer id;

    @Column(name = "contract_start_date")
    private LocalDateTime startDate;

    @Column(name = "contract_end_date")
    private LocalDateTime endDate;

    @Column(name = "contract_status")
    private int status;

    @Column(name = "contract_date")
    private LocalDateTime contractDate; //TODO: change schema to use now() for this in DB and change name to created_at

}
