package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import java.time.LocalDateTime;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.InvoiceBatchEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceBatchRepositoryJpa extends JpaRepository<InvoiceBatchEntityJpa, Integer> {

    @Query("SELECT ib FROM InvoiceBatchEntityJpa ib WHERE ib.invoiceBatchLimitDate >= ?1")
    InvoiceBatchEntityJpa getCurrentBatch(LocalDateTime currentDateTime);

}
