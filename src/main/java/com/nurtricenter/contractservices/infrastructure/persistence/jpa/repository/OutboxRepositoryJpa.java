package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.OutboxEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboxRepositoryJpa extends JpaRepository<OutboxEntityJpa, UUID> {
}
