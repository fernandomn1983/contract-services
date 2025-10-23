package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.presentation.dto.ModifyContractRequestBody;
import com.nurtricenter.contractservices.presentation.dto.ModifyContractResponseBody;

public class ModifyContractServiceUseCaseCommand implements Command<ModifyContractResponseBody> {

    public ModifyContractRequestBody modifyContractRequestBody;

    public ModifyContractServiceUseCaseCommand(ModifyContractRequestBody modifyContractRequestBody) {
        this.modifyContractRequestBody = modifyContractRequestBody;
    }

}
