package com.nurtricenter.contractservices.domain.aggregatecontract.payment;

public interface PaymentRepository {

    PaymentDomain createPayment(PaymentDomain paymentDomain);

}
