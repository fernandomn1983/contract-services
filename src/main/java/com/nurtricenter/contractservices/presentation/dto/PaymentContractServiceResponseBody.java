package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentContractServiceResponseBody {
    
    private Integer contractId;
    private Integer paymentId;
    private PaymentInvoiceResponseBody invoice;

}
