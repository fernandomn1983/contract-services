package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentContractServiceRequestBody {
    
    private int contractId;
    private String invoiceFullName;
    private String invoiceIdentifyDocument;

}
