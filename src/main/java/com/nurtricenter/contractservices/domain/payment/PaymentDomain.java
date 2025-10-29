package com.nurtricenter.contractservices.domain.payment;

import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Money;

public class PaymentDomain {

    private int paymentId;
    private Money totalPrice;
    private InvoiceDomain invoiceDomain;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public InvoiceDomain getInvoiceDomain() {
        return invoiceDomain;
    }

    public void setInvoiceDomain(InvoiceDomain invoiceDomain) {
        this.invoiceDomain = invoiceDomain;
    }

}
