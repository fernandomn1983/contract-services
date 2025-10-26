package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PrepareContractServicesResponseBody {

    private UUID serviceId;
    private int quantity;

}
