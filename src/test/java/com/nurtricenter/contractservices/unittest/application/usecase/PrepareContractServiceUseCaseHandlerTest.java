package com.nurtricenter.contractservices.unittest.application.usecase;

import com.nurtricenter.contractservices.application.repository.ContractRepositoryImpl;
import com.nurtricenter.contractservices.application.usecase.PrepareContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.application.usecase.PrepareContractServiceUseCaseHandler;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractServicesRequestBody;
import com.nurtricenter.contractservices.presentation.mapper.PrepareContractMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")
class PrepareContractServiceUseCaseHandlerTest {

    private static PrepareContractServiceUseCaseHandler sut;

    private static ContractRepository contractRepository;
    private static PrepareContractMapper prepareContractMapper;

    @BeforeAll
    static void setUp() {
        contractRepository = mock(ContractRepositoryImpl.class);
        prepareContractMapper = mock(PrepareContractMapper.class);

        sut = new PrepareContractServiceUseCaseHandler(
                contractRepository,
                prepareContractMapper);
    }

    @Test
    void prepareContract_whenPatientAndServiceExist_contractIsPreparedSuccessfully() {
        String patientId = "2ef33ff3-7360-4abe-8dfd-d27f5c2fd8d8";
        PrepareContractRequestBody prepareContractRequestBody = new PrepareContractRequestBody();
        prepareContractRequestBody.setPatientId(patientId);
        prepareContractRequestBody.setContractEndDate(LocalDateTime.of(2026, 1, 1, 0, 0));
        prepareContractRequestBody.setContractStartDate(LocalDateTime.of(2026, 2, 1, 0, 0));
        PrepareContractServicesRequestBody prepareContractServicesRequestBody = new PrepareContractServicesRequestBody();
        String serviceId = "f81dd82a-2b7c-4c24-83ed-3634be7381d3";
        prepareContractServicesRequestBody.setServiceId(UUID.fromString(serviceId));
        prepareContractServicesRequestBody.setQuantity(1);
        prepareContractRequestBody.setServices(List.of(prepareContractServicesRequestBody));

        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(1);
        contractDomain.setContractStartDate(LocalDateTime.of(2026, 1, 1, 0, 0));
        contractDomain.setContractEndDate(LocalDateTime.of(2026, 2, 1, 0, 0));
        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId(patientId);
        contractDomain.setPatientDomain(patientDomain);
        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setServiceId(UUID.fromString(serviceId));
        contractDomain.setServiceDomainList(List.of(serviceDomain));

        when(contractRepository.createContract(contractDomain)).thenReturn(contractDomain);

        PrepareContractServiceUseCaseCommand command = new PrepareContractServiceUseCaseCommand(prepareContractRequestBody);

        sut.handle(command);

        Assertions.assertEquals(contractDomain.getPatientDomain().getPatientId(), prepareContractRequestBody.getPatientId());
    }
}