package com.nurtricenter.contractservices.presentation.mapper;

import com.nurtricenter.contractservices.domain.aggregatecontract.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.aggregatepatient.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.aggregateservice.service.ServiceDomain;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractResponseBody;
import org.springframework.stereotype.Component;

@Component
public class PrepareContractMapper {

    public PrepareContractResponseBody toResponse(
            ContractDomain contractDomain,
            PatientDomain patientDomain,
            ServiceDomain serviceDomain
    ) {
        PrepareContractResponseBody prepareContractResponseBody = new PrepareContractResponseBody();

        prepareContractResponseBody.setContractId(contractDomain.getContractId());
        prepareContractResponseBody.setPatientName(patientDomain.getFirstName());
        prepareContractResponseBody.setPatientLastName(patientDomain.getLastName());
        prepareContractResponseBody.setServiceName(serviceDomain.getDescription());
        prepareContractResponseBody.setContractStatus(contractDomain.getContractStatus().getDescription());

        return prepareContractResponseBody;
    }

}
