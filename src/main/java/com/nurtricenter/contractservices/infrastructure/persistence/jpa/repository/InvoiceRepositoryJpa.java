package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.InvoiceEntityJpa;

@Repository
public interface InvoiceRepositoryJpa extends JpaRepository<InvoiceEntityJpa, Integer> {

    @Query("select max(iej.invoiceNumber) from InvoiceEntityJpa iej")
    int getMaxInvoiceNumber();

}
