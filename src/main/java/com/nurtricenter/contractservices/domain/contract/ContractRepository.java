package com.nurtricenter.contractservices.domain.contract;

public interface ContractRepository {

    ContractDomain createContract(ContractDomain contractDomain);
    ContractDomain getContract(Integer contractId);
    void cancelContract(Integer contractId);

}
