package com.nurtricenter.contractservices.application.usecase;

import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceResponseBody;

import an.awesome.pipelinr.Command;

public class PayContractServiceUseCaseCommand implements Command<PaymentContractServiceResponseBody> {

    public final PaymentContractServiceRequestBody paymentRequestBody;

    public PayContractServiceUseCaseCommand(PaymentContractServiceRequestBody paymentRequestBody) {
        this.paymentRequestBody = paymentRequestBody;
    }
    
}
