package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class InvoiceEntityJpa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer id;

    @Column(name = "invoice_identity_document")
    private String identityDocument;
    
    @Column(name = "invoice_full_name")
    private String fullName;

    @Column(name = "invoice_number")
    private int invoiceNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @ManyToOne
    @JoinColumn(name = "invoice_batch_id")
    private InvoiceBatchEntityJpa invoiceBatchEntityJpa;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentEntityJpa paymentEntityJpa;

}
