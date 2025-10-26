package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.nurtricenter.contractservices.domain.contract.ContractRepository;

@Component
@RequiredArgsConstructor
public class CancelContractServiceUseCaseHandler implements Command.Handler<CancelContractServiceUseCaseCommand, Void> {

    private final ContractRepository contractRepository;

    @Override
    public Void handle(CancelContractServiceUseCaseCommand cancelContractServiceUseCaseCommand) {
        contractRepository.cancelContract(cancelContractServiceUseCaseCommand.contractId);
        
        return null;
    }

}
