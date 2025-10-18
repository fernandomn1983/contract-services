package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepositoryJpa extends JpaRepository<PatientEntityJpa, Integer> {
}
