package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.domain.valueobjects.Status;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractResponseBody;
import com.nurtricenter.contractservices.presentation.mapper.PrepareContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PrepareContractServiceUseCaseHandler implements Command.Handler<PrepareContractServiceUseCaseCommand, PrepareContractResponseBody> {

    private final ContractRepository contractRepository;
    private final PrepareContractMapper prepareContractMapper;

    @Override
    public PrepareContractResponseBody handle(PrepareContractServiceUseCaseCommand prepareContractServiceUseCaseCommand) {
        ContractDomain contractDomain = prepareContractDomain(prepareContractServiceUseCaseCommand);

        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getPatientId());

        List<ServiceDomain> serviceDomainList = prepareContractServiceUseCaseCommand.prepareContractRequestBody.getServices()
                .stream()
                .map(prepareContractServicesRequestBody -> {
                    ServiceDomain serviceDomain = new ServiceDomain();
                    serviceDomain.setServiceId(prepareContractServicesRequestBody.getServiceId());
                    serviceDomain.setQuantity(new Quantity(prepareContractServicesRequestBody.getQuantity()));

                    return serviceDomain;
                })
                .toList();
        contractDomain.setServiceDomainList(serviceDomainList);
        contractDomain.setPatientDomain(patientDomain);

        ContractDomain contractDomainSaved = contractRepository.createContract(contractDomain);

        return prepareContractMapper.toResponse(contractDomainSaved);
    }

    private ContractDomain prepareContractDomain(PrepareContractServiceUseCaseCommand prepareContractServiceUseCaseCommand) {
        ContractDomain contractDomain = new ContractDomain();

        contractDomain.setContractStartDate(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getContractStartDate());
        contractDomain.setContractEndDate(prepareContractServiceUseCaseCommand.prepareContractRequestBody.getContractEndDate());
        contractDomain.setContractStatus(Status.PREPARED);

        return contractDomain;
    }

}
