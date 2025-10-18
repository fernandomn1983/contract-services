package com.nurtricenter.contractservices.presentation.controller;

import an.awesome.pipelinr.Pipeline;
import com.nurtricenter.contractservices.application.usecase.PrepareContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.domain.shared.exception.NotFoundException;
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

}
