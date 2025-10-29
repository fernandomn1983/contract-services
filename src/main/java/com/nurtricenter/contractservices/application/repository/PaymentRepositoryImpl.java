package com.nurtricenter.contractservices.application.repository;

import com.nurtricenter.contractservices.application.mapper.PaymentMapper;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDetailDomain;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentRepository;
import com.nurtricenter.contractservices.domain.valueobjects.Status;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.*;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ContractRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.InvoiceBatchRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.InvoiceRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PaymentRepositoryJpa;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private static final String CONTRACT_ID_MSG_FORMAT_EXCEPTION = "ContractId = %s";

    private final PaymentRepositoryJpa paymentRepositoryJpa;
    private final ContractRepositoryJpa contractRepositoryJpa;
    private final InvoiceRepositoryJpa invoiceRepositoryJpa;
    private final InvoiceBatchRepositoryJpa invoiceBatchRepositoryJpa;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDomain pay(PaymentDomain paymentDomain, ContractDomain contractDomain) {
        PaymentEntityJpa paymentEntityJpa = new PaymentEntityJpa();
        paymentEntityJpa.setTotalCost(paymentDomain.getTotalPrice().getAmount());

        Optional<ContractEntityJpa> optionalPreparedContractEntityJpa = contractRepositoryJpa.findPreparedContractByContractId(contractDomain.getContractId());
        if (optionalPreparedContractEntityJpa.isEmpty()) {
            throw new NotFoundException(String.format(CONTRACT_ID_MSG_FORMAT_EXCEPTION, contractDomain.getContractId()));
        }
        ContractEntityJpa contractEntityJpa = optionalPreparedContractEntityJpa.get();
        contractEntityJpa.setStatus(Status.PAID.getCode());
        paymentEntityJpa.setContractEntityJpa(contractEntityJpa);

        InvoiceEntityJpa invoiceEntityJpa = prepareInvoiceEntityJpa(paymentDomain.getInvoiceDomain());
        int currentInvoiceNumber = invoiceRepositoryJpa.getMaxInvoiceNumber();
        invoiceEntityJpa.setInvoiceNumber(currentInvoiceNumber + 1);

        InvoiceBatchEntityJpa invoiceBatchEntityJpa = invoiceBatchRepositoryJpa.getCurrentBatch(LocalDateTime.now());
        invoiceEntityJpa.setInvoiceBatchEntityJpa(invoiceBatchEntityJpa);
        paymentEntityJpa.setInvoiceEntityJpa(invoiceEntityJpa);
        invoiceEntityJpa.setPaymentEntityJpa(paymentEntityJpa);

        PaymentEntityJpa paymentEntityJpaSaved = paymentRepositoryJpa.save(paymentEntityJpa);

        PaymentDomain paymentDomainSaved = paymentMapper.toDomain(paymentEntityJpaSaved);
        paymentDomainSaved.getInvoiceDomain().setTotalAmount(paymentDomain.getTotalPrice().getAmount());
        paymentDomainSaved.getInvoiceDomain().setInvoiceDetailList(prepareInvoiceDetails(contractEntityJpa));

        return paymentDomainSaved;
    }

    private InvoiceEntityJpa prepareInvoiceEntityJpa(InvoiceDomain invoiceDomain) {
        InvoiceEntityJpa invoiceEntityJpa = new InvoiceEntityJpa();

        invoiceEntityJpa.setIdentityDocument(invoiceDomain.getDocumentId());
        invoiceEntityJpa.setFullName(invoiceDomain.getFullName());
        invoiceEntityJpa.setInvoiceDate(invoiceDomain.getInvoiceDate());

        return invoiceEntityJpa;
    }

    private List<InvoiceDetailDomain> prepareInvoiceDetails(ContractEntityJpa contractEntityJpa) {
        return contractEntityJpa.getServices()
                .stream()
                .map(contractServiceEntityJpa -> {
                    InvoiceDetailDomain invoiceDetailDomain = new InvoiceDetailDomain();

                    invoiceDetailDomain.setServiceId(contractServiceEntityJpa.getServiceId().getId());
                    invoiceDetailDomain.setServiceName(contractServiceEntityJpa.getServiceId().getDescription());
                    invoiceDetailDomain.setUnitPrice(contractServiceEntityJpa.getServiceId().getCost());
                    invoiceDetailDomain.setQuantity(contractServiceEntityJpa.getQuantity());
                    invoiceDetailDomain.setTotalAmount(calculateTotalAmount(contractServiceEntityJpa));

                    return invoiceDetailDomain;
                }).toList();
    }

    private BigDecimal calculateTotalAmount(ContractServiceEntityJpa contractServiceEntityJpa) {
        BigDecimal cost = contractServiceEntityJpa.getServiceId().getCost();
        BigDecimal quantity = new BigDecimal(contractServiceEntityJpa.getQuantity());

        return cost.multiply(quantity);
    }

}
