package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractResponseBody;

public class PrepareContractServiceUseCaseCommand implements Command<PrepareContractResponseBody> {

    public PrepareContractRequestBody prepareContractRequestBody;

    public PrepareContractServiceUseCaseCommand(PrepareContractRequestBody prepareContractRequestBody){
        this.prepareContractRequestBody = prepareContractRequestBody;
    }

}
