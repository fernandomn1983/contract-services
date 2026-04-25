package com.nurtricenter.contractservices.unittest.application.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nurtricenter.contractservices.application.mapper.PatientMapper;
import com.nurtricenter.contractservices.application.repository.PatientRepositoryImpl;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.patient.PatientRepository;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PatientRepositoryJpa;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class PatientRepositoryImplTest {

    private static PatientRepository sut;

    private static PatientRepositoryJpa patientRepositoryJpa;
    private static PatientMapper patientMapper;

    @BeforeAll
    static void setUpClass() {
        patientRepositoryJpa = mock(PatientRepositoryJpa.class);
        patientMapper = mock(PatientMapper.class);

        sut = new PatientRepositoryImpl(patientRepositoryJpa, patientMapper);
    }

    @Test
    void upsertPatient_whenSendingCorrectValue_upsertsSuccessfully() {
        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId("d952d834-2330-4323-8e95-e1d00d7b012c");
        patientDomain.setFirstName("FirstName");
        patientDomain.setLastName("LastName");

        PatientEntityJpa patientEntityJpa = new PatientEntityJpa();
        patientEntityJpa.setId(UUID.fromString("d952d834-2330-4323-8e95-e1d00d7b012c"));
        patientEntityJpa.setName("FirstName");
        patientEntityJpa.setLastName("LastName");

        when(patientMapper.toJpaEntity(patientDomain)).thenReturn(patientEntityJpa);
        when(patientRepositoryJpa.save(any(PatientEntityJpa.class))).thenReturn(patientEntityJpa);
        when(patientMapper.toDomain(patientEntityJpa)).thenReturn(patientDomain);

        PatientDomain result = sut.upsertPatient(patientDomain);

        assertNotNull(result);
    }
}
