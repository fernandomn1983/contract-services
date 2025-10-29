package com.nurtricenter.contractservices.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PaymentInvoiceResponseBody {
    
    private String taxIdentifier;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String invoiceFullName;
    private String invoiceIdentityDocument;
    private LocalDate invoiceLimitDate;
    private BigDecimal totalAmount;
    List<PaymentInvoiceDetailResponseBody> details;
}
