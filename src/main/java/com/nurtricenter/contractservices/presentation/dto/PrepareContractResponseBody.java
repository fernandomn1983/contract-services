package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrepareContractResponseBody {

    private int contractId;
    private String patientName;
    private String patientLastName;
    private String serviceName;
    private String contractStatus;

}
