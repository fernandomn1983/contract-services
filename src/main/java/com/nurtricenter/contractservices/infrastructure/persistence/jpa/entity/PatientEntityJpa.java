package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntityJpa {

    @Id
    @Column(name = "patient_id")
    private Integer id;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "patient_last_name")
    private String lastName;

    @Column(name = "patient_identity_document")
    private String identityDocument;

}
