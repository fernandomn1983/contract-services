package com.nurtricenter.contractservices.presentation.controller;

import an.awesome.pipelinr.Pipeline;
import com.nurtricenter.contractservices.application.usecase.CancelContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.application.usecase.PayContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.application.usecase.PrepareContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.shared.exception.InvalidValueException;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceRequestBody;
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
    public ResponseEntity<?> prepareContractService(
            @RequestBody PrepareContractRequestBody prepareContractRequestBody) {
        try {
            PrepareContractServiceUseCaseCommand prepareContractServiceUseCaseCommand = new PrepareContractServiceUseCaseCommand(
                    prepareContractRequestBody);

            return ResponseEntity.ok(prepareContractServiceUseCaseCommand.execute(pipeline));
        } catch (InvalidValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{contractId}/cancellations")
    @ResponseStatus
    public ResponseEntity<?> cancelContractService(@PathVariable int contractId) {
        try {
            CancelContractServiceUseCaseCommand cancelContractServiceUseCaseCommand = new CancelContractServiceUseCaseCommand(
                    contractId);
            cancelContractServiceUseCaseCommand.execute(pipeline);

            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{contractId}/payments")
    @ResponseStatus
    public ResponseEntity<?> payContractService(@PathVariable int contractId, @RequestBody PaymentContractServiceRequestBody paymentRequestBody) {
        try {
            PayContractServiceUseCaseCommand payContractServiceUseCaseCommand = new PayContractServiceUseCaseCommand(paymentRequestBody);

            return ResponseEntity.ok(payContractServiceUseCaseCommand.execute(pipeline));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
