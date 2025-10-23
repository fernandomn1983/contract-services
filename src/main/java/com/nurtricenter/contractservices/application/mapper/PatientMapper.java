package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "id", target = "patientId")
    @Mapping(source = "name", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "identityDocument", target = "idDocument")
    PatientDomain toDomain(PatientEntityJpa patientEntity);

    @Mapping(source = "patientId", target = "id")
    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "idDocument", target = "identityDocument")
    PatientEntityJpa toJpaEntity(PatientDomain patientDomain);

}
