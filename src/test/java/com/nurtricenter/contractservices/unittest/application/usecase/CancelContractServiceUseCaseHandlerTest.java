package com.nurtricenter.contractservices.unittest.application.usecase;

import com.nurtricenter.contractservices.application.repository.ContractRepositoryImpl;
import com.nurtricenter.contractservices.application.usecase.CancelContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.application.usecase.CancelContractServiceUseCaseHandler;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")
class CancelContractServiceUseCaseHandlerTest {

    private static CancelContractServiceUseCaseHandler sut;

    private static ContractRepository contractRepository;

    @BeforeAll
    static void setUpClass() {
        contractRepository = mock(ContractRepositoryImpl.class);
        sut = new CancelContractServiceUseCaseHandler(contractRepository);
    }

    @Test
    void cancelContract_whenSendingValidData_contractIsCancelledSuccessfully() {
        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId("2ef33ff3-7360-4abe-8dfd-d27f5c2fd8d8");
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(1);
        contractDomain.setPatientDomain(patientDomain);
        contractDomain.setContractStatus(Status.PREPARED);
        when(contractRepository.getContract(1)).thenReturn(contractDomain);

        CancelContractServiceUseCaseCommand command = new CancelContractServiceUseCaseCommand(1);

        sut.handle(command);

        Assertions.assertEquals(Status.CANCELED, contractDomain.getContractStatus());
    }
}