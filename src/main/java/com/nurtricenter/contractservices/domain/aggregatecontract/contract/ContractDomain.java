package com.nurtricenter.contractservices.domain.aggregatecontract.contract;

import com.nurtricenter.contractservices.domain.shared.valueobjects.Status;

import java.time.LocalDateTime;

public class ContractDomain {

    private int contractId;
    private LocalDateTime date;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private Status contractStatus;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDateTime contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public LocalDateTime getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDateTime contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Status getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Status contractStatus) {
        this.contractStatus = contractStatus;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

}
