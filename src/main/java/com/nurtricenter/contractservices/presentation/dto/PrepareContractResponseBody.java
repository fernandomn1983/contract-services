package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PrepareContractResponseBody {

    private int contractId;
    private int patientId;
    private int contractStatus;
    List<PrepareContractServicesResponseBody> services;

}
