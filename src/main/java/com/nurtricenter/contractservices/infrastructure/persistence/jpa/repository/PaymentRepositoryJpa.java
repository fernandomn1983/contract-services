package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PaymentEntityJpa;

@Repository
public interface PaymentRepositoryJpa extends JpaRepository<PaymentEntityJpa, Integer> {
}
