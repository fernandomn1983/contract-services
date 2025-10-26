package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;

public class CancelContractServiceUseCaseCommand implements Command<Void> {

    public final Integer contractId;

    public CancelContractServiceUseCaseCommand(Integer contractId) {
        this.contractId = contractId;
    }

}
