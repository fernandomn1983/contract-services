package com.nurtricenter.contractservices.unittest.application.repository;

import com.nurtricenter.contractservices.application.mapper.PaymentMapper;
import com.nurtricenter.contractservices.application.repository.PaymentRepositoryImpl;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentRepository;
import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.*;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ContractRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.InvoiceBatchRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.InvoiceRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PaymentRepositoryJpa;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")
class PaymentRepositoryImplTest {

    private static PaymentRepository sut;

    private static PaymentRepositoryJpa paymentRepositoryJpa;
    private static ContractRepositoryJpa contractRepositoryJpa;
    private static InvoiceRepositoryJpa invoiceRepositoryJpa;
    private static InvoiceBatchRepositoryJpa invoiceBatchRepositoryJpa;
    private static PaymentMapper paymentMapper;

    @BeforeAll
    static void setUpClass() {
        paymentRepositoryJpa = mock(PaymentRepositoryJpa.class);
        contractRepositoryJpa = mock(ContractRepositoryJpa.class);
        invoiceRepositoryJpa = mock(InvoiceRepositoryJpa.class);
        invoiceBatchRepositoryJpa = mock(InvoiceBatchRepositoryJpa.class);
        paymentMapper = mock(PaymentMapper.class);

        sut = new PaymentRepositoryImpl(
                paymentRepositoryJpa,
                contractRepositoryJpa,
                invoiceRepositoryJpa,
                invoiceBatchRepositoryJpa,
                paymentMapper
        );
    }

    @Test
    void payContract_whenSendingNonExistentPreparedContract_throwsNotFoundException() {
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(10);
        Optional<ContractEntityJpa> optionalPreparedContractEntityJpa = Optional.empty();
        when(contractRepositoryJpa.findPreparedContractByContractId(contractDomain.getContractId())).thenReturn(optionalPreparedContractEntityJpa);
        PaymentDomain paymentDomain = new PaymentDomain();

        Assertions.assertThrows(NotFoundException.class, () -> sut.pay(paymentDomain, contractDomain));
    }

    @Test
    void payContract_whenSendingExistentPreparedContract_processFinishesSuccessfully() {
        int contractId = 1;
        String serviceId = "f81dd82a-2b7c-4c24-83ed-3634be7381d3";
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(contractId);
        contractDomain.pay();

        ServiceEntityJpa serviceEntityJpa = new ServiceEntityJpa();
        serviceEntityJpa.setId(UUID.fromString(serviceId));
        serviceEntityJpa.setCost(new BigDecimal("20.0"));

        ContractEntityJpa contractEntityJpa = new ContractEntityJpa();
        Optional<ContractEntityJpa> optionalPreparedContractEntityJpa = Optional.of(contractEntityJpa);

        ContractServiceEntityJpa contractServiceEntityJpa = new ContractServiceEntityJpa();
        contractServiceEntityJpa.setContractId(contractEntityJpa);
        contractServiceEntityJpa.setServiceId(serviceEntityJpa);
        contractServiceEntityJpa.setQuantity(1);

        contractEntityJpa.setServices(List.of(contractServiceEntityJpa));

        InvoiceDomain invoiceDomain = new InvoiceDomain();
        invoiceDomain.setDocumentId("5622486");
        invoiceDomain.setFullName("John Doe");
        invoiceDomain.setInvoiceDate(LocalDate.of(2026, 1, 16));

        PaymentDomain paymentDomain = new PaymentDomain();
        paymentDomain.setTotalPrice(new Money(new BigDecimal("25.0")));
        paymentDomain.setInvoiceDomain(invoiceDomain);

        InvoiceBatchEntityJpa invoiceBatchEntityJpa = new InvoiceBatchEntityJpa();
        invoiceBatchEntityJpa.setInvoiceBatchLimitDate(LocalDateTime.of(2026, 1, 1, 0, 0));

        PaymentEntityJpa paymentEntityJpaCreated = new PaymentEntityJpa();
        paymentEntityJpaCreated.setId(1);

        PaymentDomain paymentDomainSaved = new PaymentDomain();
        paymentDomainSaved.setInvoiceDomain(invoiceDomain);

        when(contractRepositoryJpa.findPreparedContractByContractId(contractId)).thenReturn(optionalPreparedContractEntityJpa);
        when(invoiceRepositoryJpa.getMaxInvoiceNumber()).thenReturn(1);
        when(invoiceBatchRepositoryJpa.getCurrentBatch(any(LocalDateTime.class))).thenReturn(invoiceBatchEntityJpa);
        when(paymentRepositoryJpa.save(any(PaymentEntityJpa.class))).thenReturn(paymentEntityJpaCreated);
        when(paymentMapper.toDomain(paymentEntityJpaCreated)).thenReturn(paymentDomainSaved);

        Assertions.assertDoesNotThrow(() -> sut.pay(paymentDomain, contractDomain));
    }

}