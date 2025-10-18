package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PrepareContractRequestBody {

    private int patientId;
    private UUID serviceId;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;

}
