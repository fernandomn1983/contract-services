package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "contractId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContractServiceEntityJpa> services;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntityJpa patient;

}
