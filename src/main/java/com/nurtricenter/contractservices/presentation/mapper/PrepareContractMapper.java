package com.nurtricenter.contractservices.presentation.mapper;

import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractResponseBody;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractServicesRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractServicesResponseBody;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrepareContractMapper {

    public PrepareContractResponseBody toResponse(
            ContractDomain contractDomain,
            PatientDomain patientDomain,
            List<ServiceDomain> serviceDomainList
    ) {
        PrepareContractResponseBody prepareContractResponseBody = new PrepareContractResponseBody();

        prepareContractResponseBody.setContractId(contractDomain.getContractId());
        prepareContractResponseBody.setPatientId(patientDomain.getPatientId());
        prepareContractResponseBody.setContractStatus(contractDomain.getContractStatus().getCode());

        prepareContractResponseBody.setServices(serviceDomainList
                .stream()
                .map(this::toResponse)
                .toList());

        return prepareContractResponseBody;
    }

    public PrepareContractResponseBody toResponse(
            ContractDomain contractDomain
    ) {
        PrepareContractResponseBody prepareContractResponseBody = new PrepareContractResponseBody();

        prepareContractResponseBody.setContractId(contractDomain.getContractId());
        prepareContractResponseBody.setPatientId(contractDomain.getPatientDomain().getPatientId());
        prepareContractResponseBody.setContractStatus(contractDomain.getContractStatus().getCode());

        prepareContractResponseBody.setServices(contractDomain.getServiceDomainList()
                .stream()
                .map(this::toResponse)
                .toList());

        return prepareContractResponseBody;
    }

    public ServiceDomain toDomain(PrepareContractServicesRequestBody prepareContractServicesRequestBody) {
        ServiceDomain serviceDomain = new ServiceDomain();

        serviceDomain.setServiceId(prepareContractServicesRequestBody.getServiceId());

        return serviceDomain;
    }

    public PrepareContractServicesResponseBody toResponse(ServiceDomain serviceDomain) {
        PrepareContractServicesResponseBody prepareContractServicesResponseBody = new PrepareContractServicesResponseBody();

        prepareContractServicesResponseBody.setServiceId(serviceDomain.getServiceId());
        prepareContractServicesResponseBody.setQuantity(serviceDomain.getQuantity().getValue());

        return prepareContractServicesResponseBody;
    }

}
