package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PrepareContractRequestBody {

    private int patientId;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private List<PrepareContractServicesRequestBody> services;

}
