package com.nurtricenter.contractservices.unittest.application.repository;

import com.nurtricenter.contractservices.application.mapper.ContractMapper;
import com.nurtricenter.contractservices.application.repository.ContractRepositoryImpl;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ContractRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PatientRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ServiceRepositoryJpa;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractRepositoryImplTest {

    private static ContractRepositoryImpl sut;

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

}