package com.nurtricenter.contractservices.domain.payment;

import com.nurtricenter.contractservices.domain.contract.ContractDomain;

public interface PaymentRepository {

    PaymentDomain pay(PaymentDomain paymentDomain, ContractDomain contractDomain);

}
