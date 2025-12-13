package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceResponseBody;

public class PayContractServiceUseCaseCommand implements Command<PaymentContractServiceResponseBody> {

    public final int contractId;
    public final PaymentContractServiceRequestBody paymentRequestBody;

    public PayContractServiceUseCaseCommand(int contractId, PaymentContractServiceRequestBody paymentRequestBody) {
        this.contractId = contractId;
        this.paymentRequestBody = paymentRequestBody;
    }

}
