package com.nurtricenter.contractservices.application.repository;

import com.nurtricenter.contractservices.application.mapper.PatientMapper;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.patient.PatientRepository;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PatientRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {

    private final PatientRepositoryJpa patientRepositoryJpa;
    private final PatientMapper patientMapper;

    @Override
    public PatientDomain upsertPatient(PatientDomain patientDomain) {
        PatientEntityJpa patientEntityJpa = patientMapper.toJpaEntity(patientDomain);
        PatientEntityJpa patientEntityJpaResult = patientRepositoryJpa.save(patientEntityJpa);

        return patientMapper.toDomain(patientEntityJpaResult);
    }

}
