package com.nurtricenter.contractservices.domain.aggregatecontract.contract;

import java.util.UUID;

public interface ContractRepository {

    ContractDomain createContract(ContractDomain contractDomain);
    ContractDomain updateContract(ContractDomain contractDomain);
    ContractDomain getContract(UUID contractId);

}
