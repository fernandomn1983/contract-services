package com.nurtricenter.contractservices.presentation.controller;

import an.awesome.pipelinr.Pipeline;
import com.nurtricenter.contractservices.application.usecase.ModifyContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.application.usecase.PrepareContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import com.nurtricenter.contractservices.presentation.dto.ModifyContractRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PrepareContractRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contract-services/contracts")
@RequiredArgsConstructor
public class ContractServiceController {

    private final Pipeline pipeline;

    @PostMapping
    @ResponseStatus
    public ResponseEntity<?> prepareContractService(@RequestBody PrepareContractRequestBody prepareContractRequestBody) {
        try {
            PrepareContractServiceUseCaseCommand prepareContractServiceUseCaseCommand = new PrepareContractServiceUseCaseCommand(prepareContractRequestBody);

            return ResponseEntity.ok(prepareContractServiceUseCaseCommand.execute(pipeline));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{contractId}")
    @ResponseStatus
    public ResponseEntity<?> modifyContractService(@PathVariable int contractId, @RequestBody ModifyContractRequestBody modifyContractRequestBody) {
        try {
            ModifyContractServiceUseCaseCommand modifyContractServiceUseCaseCommand = new ModifyContractServiceUseCaseCommand(modifyContractRequestBody);

            return ResponseEntity.ok(modifyContractServiceUseCaseCommand.execute(pipeline));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
