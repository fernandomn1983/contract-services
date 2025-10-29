package com.nurtricenter.contractservices.presentation.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInvoiceDetailResponseBody {
    
    private UUID serviceId;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;

}
