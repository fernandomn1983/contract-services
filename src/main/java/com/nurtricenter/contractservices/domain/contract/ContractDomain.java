package com.nurtricenter.contractservices.domain.contract;

import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Status;

import java.time.LocalDateTime;
import java.util.List;

public class ContractDomain {

    private int contractId;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private Status contractStatus;

    private PatientDomain patientDomain;

    private List<ServiceDomain> serviceDomainList;

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

    public PatientDomain getPatientDomain() {
        return patientDomain;
    }

    public void setPatientDomain(PatientDomain patientDomain) {
        this.patientDomain = patientDomain;
    }

    public List<ServiceDomain> getServiceDomainList() {
        return serviceDomainList;
    }

    public void setServiceDomainList(List<ServiceDomain> serviceDomainList) {
        this.serviceDomainList = serviceDomainList;
    }


}
