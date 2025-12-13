package com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository;

import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.InvoiceEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepositoryJpa extends JpaRepository<InvoiceEntityJpa, Integer> {

    @Query("select coalesce(max(iej.invoiceNumber), 0) from InvoiceEntityJpa iej")
    int getMaxInvoiceNumber();

}
