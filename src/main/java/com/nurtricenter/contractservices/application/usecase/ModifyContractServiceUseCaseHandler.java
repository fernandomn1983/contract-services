package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.presentation.dto.ModifyContractResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModifyContractServiceUseCaseHandler implements Command.Handler<ModifyContractServiceUseCaseCommand, ModifyContractResponseBody> {

    @Override
    public ModifyContractResponseBody handle(ModifyContractServiceUseCaseCommand modifyContractServiceUseCaseCommand) {
        // TODO: get contract
        // TODO: get service
        // TODO: get patient
        // TODO: Prepare modification
        // TODO: Save changes
        // TODO: Map & return
        return null;
    }

}
