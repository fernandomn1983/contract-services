package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepositoryJpa extends JpaRepository<ContractEntityJpa, Integer> {

    @Query("SELECT c FROM ContractEntityJpa c WHERE c.id = ?1 AND c.status = 1")
    Optional<ContractEntityJpa> findPreparedContractByContractId(Integer contractId);

}
