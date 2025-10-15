package com.nurtricenter.contractservices.domain.aggregatecontract.contract;

import java.time.LocalDate;
import java.util.UUID;

public class Contract {

    private UUID contractId;
    private LocalDate date;

    public UUID getContractId() {
        return contractId;
    }

    public void setContractId(UUID contractId) {
        this.contractId = contractId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
