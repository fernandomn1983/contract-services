package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice_batch")
@Getter
@Setter
public class InvoiceBatchEntityJpa {
    
    @Id
    @Column(name = "invoice_batch_id")
    private Integer id;

    @Column(name = "invoice_batch_limit_date")
    private LocalDateTime invoiceBatchLimitDate;
    
}
