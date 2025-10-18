package com.nurtricenter.contractservices.infrastructure.repository;

import com.nurtricenter.contractservices.application.mapper.ContractMapper;
import com.nurtricenter.contractservices.domain.aggregatecontract.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.aggregatecontract.contract.ContractRepository;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ContractRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepository {

    private final ContractRepositoryJpa contractRepositoryJpa;
    private final ContractMapper contractMapper;

    @Override
    public ContractDomain createContract(ContractDomain contractDomain) {
        ContractEntityJpa contractEntityJpa = contractRepositoryJpa.save(contractMapper.toEntityJpa(contractDomain));

        return contractMapper.toDomain(contractEntityJpa);
    }

    @Override
    public ContractDomain updateContract(ContractDomain contractDomain) {
        return null;
    }

    @Override
    public ContractDomain getContract(UUID contractId) {
        return null;
    }

}
