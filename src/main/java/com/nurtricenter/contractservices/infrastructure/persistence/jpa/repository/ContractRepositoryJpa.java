package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepositoryJpa extends JpaRepository<ContractEntityJpa, Integer> {
}
