package com.nurtricenter.contractservices.presentation.mapper;

import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDetailDomain;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentDomain;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceResponseBody;
import com.nurtricenter.contractservices.presentation.dto.PaymentInvoiceDetailResponseBody;
import com.nurtricenter.contractservices.presentation.dto.PaymentInvoiceResponseBody;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentContractServiceMapper {

    public PaymentContractServiceResponseBody toPaymentContractServiceResponseBody(PaymentDomain paymentDomain, ContractDomain contractDomain) {
        PaymentContractServiceResponseBody paymentContractServiceResponseBody = new PaymentContractServiceResponseBody();

        paymentContractServiceResponseBody.setContractId(contractDomain.getContractId());
        paymentContractServiceResponseBody.setPaymentId(paymentDomain.getPaymentId());
        paymentContractServiceResponseBody.setInvoice(prepareInvoiceResponseBody(paymentDomain.getInvoiceDomain()));

        return paymentContractServiceResponseBody;
    }

    private PaymentInvoiceResponseBody prepareInvoiceResponseBody(InvoiceDomain invoiceDomain) {
        PaymentInvoiceResponseBody paymentInvoiceResponseBody = new PaymentInvoiceResponseBody();

        paymentInvoiceResponseBody.setTaxIdentifier(invoiceDomain.getTaxIdentifier());
        paymentInvoiceResponseBody.setInvoiceNumber(invoiceDomain.getInvoiceNumber());
        paymentInvoiceResponseBody.setInvoiceFullName(invoiceDomain.getFullName());
        paymentInvoiceResponseBody.setInvoiceDate(invoiceDomain.getInvoiceDate());
        paymentInvoiceResponseBody.setInvoiceIdentityDocument(invoiceDomain.getDocumentId());
        paymentInvoiceResponseBody.setTotalAmount(invoiceDomain.getTotalAmount());
        paymentInvoiceResponseBody.setInvoiceLimitDate(invoiceDomain.getLimitDate());

        paymentInvoiceResponseBody.setDetails(prepareInvoiceDetails(invoiceDomain.getInvoiceDetailList()));

        return paymentInvoiceResponseBody;
    }

    private List<PaymentInvoiceDetailResponseBody> prepareInvoiceDetails(List<InvoiceDetailDomain> invoiceDetailDomainList) {
        return invoiceDetailDomainList
                .stream()
                .map(invoiceDetail -> {
                    PaymentInvoiceDetailResponseBody paymentInvoiceDetailResponseBody = new PaymentInvoiceDetailResponseBody();

                    paymentInvoiceDetailResponseBody.setServiceId(invoiceDetail.getServiceId());
                    paymentInvoiceDetailResponseBody.setQuantity(invoiceDetail.getQuantity());
                    paymentInvoiceDetailResponseBody.setUnitPrice(invoiceDetail.getUnitPrice());
                    paymentInvoiceDetailResponseBody.setTotalPrice(invoiceDetail.getTotalAmount());

                    return paymentInvoiceDetailResponseBody;
                })
                .toList();
    }

}
