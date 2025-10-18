package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.domain.aggregatecontract.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.aggregatecontract.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.aggregatepatient.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.aggregatepatient.patient.PatientRepository;
import com.nurtricenter.contractservices.domain.aggregateservice.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.aggregateservice.service.ServiceRepository;
import com.nurtricenter.contractservices.domain.shared.exception.NotFoundException;
import com.nurtricenter.contractservices.domain.shared.valueobjects.Status;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractResponseBody;
import com.nurtricenter.contractservices.presentation.mapper.PrepareContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PrepareContractServiceUseCaseHandler implements Command.Handler<PrepareContractServiceUseCaseCommand, PrepareContractResponseBody> {

    private static final String PATIENT_ID_MSG_FORMAT_EXCEPTION = "PatientId = %s";
    private static final String SERVICE_ID_MSG_FORMAT_EXCEPTION = "ServiceId = %s";

    private final PatientRepository patientRepository;
    private final ServiceRepository serviceRepository;
    private final ContractRepository contractRepository;
    private final PrepareContractMapper prepareContractMapper;

    @Override
    public PrepareContractResponseBody handle(PrepareContractServiceUseCaseCommand prepareContractServiceUseCaseCommand) {
        PatientDomain patientDomain = patientRepository.getPatient(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getPatientId());
        if (patientDomain == null) {
            throw new NotFoundException(String.format(PATIENT_ID_MSG_FORMAT_EXCEPTION, prepareContractServiceUseCaseCommand.prepareContractRequestBody.getPatientId()));
        }

        ServiceDomain serviceDomain = serviceRepository.getService(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getServiceId());
        if (serviceDomain == null) {
            throw new NotFoundException(String.format(SERVICE_ID_MSG_FORMAT_EXCEPTION, prepareContractServiceUseCaseCommand.prepareContractRequestBody.getServiceId()));
        }

        ContractDomain contractDomain = prepareContractDomain(prepareContractServiceUseCaseCommand);
        ContractDomain contractDomainSaved = contractRepository.createContract(contractDomain);

        return prepareContractMapper.toResponse(
                contractDomainSaved,
                patientDomain,
                serviceDomain
        );
    }

    private ContractDomain prepareContractDomain(PrepareContractServiceUseCaseCommand prepareContractServiceUseCaseCommand) {
        ContractDomain contractDomain = new ContractDomain();

        contractDomain.setDate(LocalDateTime.now());
        contractDomain.setContractStartDate(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getContractStartDate());
        contractDomain.setContractEndDate(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getContractEndDate());
        contractDomain.setContractStatus(Status.PREPARED);

        return contractDomain;
    }

}
