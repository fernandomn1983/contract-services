package com.nurtricenter.contractservices.infrastructure.repository;

import com.nurtricenter.contractservices.application.mapper.PatientMapper;
import com.nurtricenter.contractservices.domain.aggregatepatient.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.aggregatepatient.patient.PatientRepository;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PatientRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {

    private final PatientRepositoryJpa patientRepositoryJpa;
    private final PatientMapper patientMapper;

    @Override
    public PatientDomain getPatient(int patientId) {
        Optional<PatientEntityJpa> optionalPatientEntityJpa = patientRepositoryJpa.findById(patientId);

        return optionalPatientEntityJpa.map(patientMapper::toDomain).
                orElse(null);

    }

}
