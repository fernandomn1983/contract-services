package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntityJpa {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "patient_id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "patient_last_name")
    private String lastName;

    @Column(name = "patient_identity_document")
    private String identityDocument;

}
