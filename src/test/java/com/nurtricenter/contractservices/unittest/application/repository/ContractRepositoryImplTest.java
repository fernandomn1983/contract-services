package com.nurtricenter.contractservices.unittest.application.repository;

import com.nurtricenter.contractservices.application.mapper.ContractMapper;
import com.nurtricenter.contractservices.application.repository.ContractRepositoryImpl;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ContractRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PatientRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ServiceRepositoryJpa;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")
class ContractRepositoryImplTest {

    private static ContractRepository sut;

    private static PatientRepositoryJpa patientRepositoryJpa;
    private static ServiceRepositoryJpa serviceRepositoryJpa;
    private static ContractRepositoryJpa contractRepositoryJpa;
    private static ContractMapper contractMapper;

    @BeforeAll
    public static void setUpClass() {
        patientRepositoryJpa = mock(PatientRepositoryJpa.class);
        serviceRepositoryJpa = mock(ServiceRepositoryJpa.class);
        contractRepositoryJpa = mock(ContractRepositoryJpa.class);
        contractMapper = mock(ContractMapper.class);

        sut = new ContractRepositoryImpl(
                patientRepositoryJpa,
                serviceRepositoryJpa,
                contractRepositoryJpa,
                contractMapper
        );
    }

    @Test
    void getContract_whenSendingExistentId_returnValidContract() {
        ContractEntityJpa contractEntityJpa = new ContractEntityJpa();
        contractEntityJpa.setId(1);
        Optional<ContractEntityJpa> optionalContractEntityJpa = Optional.of(contractEntityJpa);
        when(contractRepositoryJpa.findById(1)).thenReturn(optionalContractEntityJpa);
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(1);
        when(contractMapper.toDomain(contractEntityJpa)).thenReturn(contractDomain);

        ContractDomain contractDomainTest = sut.getContract(1);

        Assertions.assertEquals(contractDomain.getContractId(), contractDomainTest.getContractId());
    }

    @Test
    void getContract_whenSendingNotExistentId_throwsNotFoundException() {
        Optional<ContractEntityJpa> optionalContractEntityJpa = Optional.empty();
        when(contractRepositoryJpa.findById(2)).thenReturn(optionalContractEntityJpa);

        Assertions.assertThrows(NotFoundException.class, () -> sut.getContract(2));
    }

    @Test
    void createContract_whenSendingInvalidPatient_throwsNotFoundException() {
        String patientId = "5006a8e3-5b50-4960-bbbe-95d13cc0c9bf";
        Optional<PatientEntityJpa> optionalPatientEntityJpa = Optional.empty();
        when(patientRepositoryJpa.findById(UUID.fromString(patientId))).thenReturn(optionalPatientEntityJpa);

        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId(patientId);
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setPatientDomain(patientDomain);

        Assertions.assertThrows(NotFoundException.class, () -> sut.createContract(contractDomain));
    }

    @Test
    void createContract_whenSendingNonExistentServices_throwsNotFoundException() {
        String serviceId = "861ced36-17dd-485b-89d8-9a9b616def6a";
        Set<UUID> serviceIds = Set.of(UUID.fromString(serviceId));
        List<ServiceEntityJpa> serviceEntityJpaList = new ArrayList<>();
        when(serviceRepositoryJpa.findAllById(serviceIds)).thenReturn(serviceEntityJpaList);

        String patientId = "2ef33ff3-7360-4abe-8dfd-d27f5c2fd8d8";
        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId(patientId);
        PatientEntityJpa patientEntityJpa = new PatientEntityJpa();
        patientEntityJpa.setId(UUID.fromString(patientId));
        Optional<PatientEntityJpa> optionalPatientEntityJpa = Optional.of(patientEntityJpa);
        when(patientRepositoryJpa.findById(UUID.fromString(patientId))).thenReturn(optionalPatientEntityJpa);

        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setServiceId(UUID.fromString(serviceId));
        serviceDomain.setQuantity(new Quantity(1));
        List<ServiceDomain> serviceDomainList = List.of(serviceDomain);
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setPatientDomain(patientDomain);
        contractDomain.setServiceDomainList(serviceDomainList);

        Assertions.assertThrows(NotFoundException.class, () -> sut.createContract(contractDomain));
    }

    @Test
    void createContract_whenSendingValidPatientAndServices_createContractSuccessfully() {
        String patientId = "2ef33ff3-7360-4abe-8dfd-d27f5c2fd8d8";
        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId(patientId);
        patientDomain.setFirstName("John");
        patientDomain.setLastName("Doe");
        patientDomain.setIdDocument("5622341");
        PatientEntityJpa patientEntityJpa = new PatientEntityJpa();
        patientEntityJpa.setId(UUID.fromString(patientId));
        Optional<PatientEntityJpa> optionalPatientEntityJpa = Optional.of(patientEntityJpa);
        when(patientRepositoryJpa.findById(UUID.fromString(patientId))).thenReturn(optionalPatientEntityJpa);

        String serviceId = "861ced36-17dd-485b-89d8-9a9b616def6a";
        Set<UUID> serviceIds = Set.of(UUID.fromString(serviceId));
        ServiceEntityJpa serviceEntityJpa = new ServiceEntityJpa();
        serviceEntityJpa.setId(UUID.fromString(serviceId));
        List<ServiceEntityJpa> serviceEntityJpaList = List.of(serviceEntityJpa);
        when(serviceRepositoryJpa.findAllById(serviceIds)).thenReturn(serviceEntityJpaList);

        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setServiceId(UUID.fromString(serviceId));
        serviceDomain.setQuantity(new Quantity(1));
        List<ServiceDomain> serviceDomainList = List.of(serviceDomain);
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setPatientDomain(patientDomain);
        contractDomain.setServiceDomainList(serviceDomainList);

        ContractEntityJpa contractEntityJpaForCreation = new ContractEntityJpa();
        contractEntityJpaForCreation.setPatient(patientEntityJpa);
        when(contractMapper.toEntityJpa(contractDomain)).thenReturn(contractEntityJpaForCreation);

        ContractEntityJpa contractEntityJpaCreated = new ContractEntityJpa();
        contractEntityJpaCreated.setId(1);
        contractEntityJpaCreated.setPatient(patientEntityJpa);
        when(contractRepositoryJpa.save(contractEntityJpaForCreation)).thenReturn(contractEntityJpaCreated);

        Assertions.assertDoesNotThrow(() -> sut.createContract(contractDomain));
        Assertions.assertEquals(contractEntityJpaCreated.getPatient().getId().toString(), contractDomain.getPatientDomain().getPatientId());
    }

    @Test
    void updateContract_whenUsingValidContract_updatesContractSuccessfully() {
        String patientId = "2ef33ff3-7360-4abe-8dfd-d27f5c2fd8d8";
        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId(patientId);
        patientDomain.setFirstName("John");
        patientDomain.setLastName("Doe");
        patientDomain.setIdDocument("5622341");

        String serviceId = "861ced36-17dd-485b-89d8-9a9b616def6a";
        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setServiceId(UUID.fromString(serviceId));
        serviceDomain.setQuantity(new Quantity(1));
        List<ServiceDomain> serviceDomainList = List.of(serviceDomain);

        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(1);
        contractDomain.setPatientDomain(patientDomain);
        contractDomain.setServiceDomainList(serviceDomainList);

        PatientEntityJpa patientEntityJpa = new PatientEntityJpa();
        patientEntityJpa.setId(UUID.fromString(patientId));

        ServiceEntityJpa serviceEntityJpa = new ServiceEntityJpa();
        serviceEntityJpa.setId(UUID.fromString(serviceId));

        ContractEntityJpa contractEntityJpaForUpdate = new ContractEntityJpa();
        contractEntityJpaForUpdate.setPatient(patientEntityJpa);

        ContractServiceEntityJpa contractServiceEntityJpa = new ContractServiceEntityJpa();
        contractServiceEntityJpa.setServiceId(serviceEntityJpa);
        contractServiceEntityJpa.setContractId(contractEntityJpaForUpdate);

        contractEntityJpaForUpdate.setServices(List.of(contractServiceEntityJpa));

        when(contractMapper.toEntityJpa(contractDomain)).thenReturn(contractEntityJpaForUpdate);
        when(contractMapper.toDomain(any(ContractEntityJpa.class))).thenReturn(contractDomain);
        when(contractRepositoryJpa.save(contractEntityJpaForUpdate)).thenReturn(contractEntityJpaForUpdate);

        contractDomain = sut.updateContract(contractDomain);

        Assertions.assertEquals(1, contractDomain.getContractId());
    }

}