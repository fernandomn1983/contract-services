package com.nurtricenter.contractservices.domain.aggregatecontract.contract;

import java.util.UUID;

public interface ContractRepository {

    Contract createContract(Contract contract);
    Contract updateContract(Contract contract);
    Contract getContract(UUID contractId);

}
