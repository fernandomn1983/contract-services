package com.nurtricenter.contractservices.domain.aggregatecontract.payment;

import com.nurtricenter.contractservices.domain.shared.valueobjects.Money;
import com.nurtricenter.contractservices.domain.shared.valueobjects.Quantity;
import com.nurtricenter.contractservices.domain.shared.valueobjects.Status;

public class PaymentDomain {

    private int paymentId;
    private Quantity quantity;
    private Money unitaryPrice;
    private Money totalPrice;
    private Status status;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Money getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(Money unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
