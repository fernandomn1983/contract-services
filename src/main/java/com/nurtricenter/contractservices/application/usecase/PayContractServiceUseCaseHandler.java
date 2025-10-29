package com.nurtricenter.contractservices.application.usecase;

import an.awesome.pipelinr.Command;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDetailDomain;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentRepository;
import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceRequestBody;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceResponseBody;
import com.nurtricenter.contractservices.presentation.mapper.PaymentContractServiceMapper;
import com.nurtricenter.contractservices.shared.value.MetadataValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class PayContractServiceUseCaseHandler implements Command.Handler<PayContractServiceUseCaseCommand, PaymentContractServiceResponseBody> {

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final MetadataValues metadataValues;
    private final PaymentContractServiceMapper paymentContractServiceMapper;

    @Override
    public PaymentContractServiceResponseBody handle(PayContractServiceUseCaseCommand payContractServiceUseCaseCommand) {
        ContractDomain contractDomain = contractRepository.getContract(payContractServiceUseCaseCommand.paymentRequestBody.getContractId());
        InvoiceDomain invoiceDomain = prepareInvoice(contractDomain, payContractServiceUseCaseCommand.paymentRequestBody);
        PaymentDomain paymentDomain = preparePayment(invoiceDomain);
        paymentDomain.setInvoiceDomain(invoiceDomain);

        PaymentDomain paymentDomainSaved = paymentRepository.pay(paymentDomain, contractDomain);
        paymentDomainSaved.getInvoiceDomain().setTaxIdentifier(metadataValues.getCompanyTaxIdentifier());

        return paymentContractServiceMapper.toPaymentContractServiceResponseBody(paymentDomainSaved, contractDomain);
    }

    private InvoiceDomain prepareInvoice(
            ContractDomain contractDomain,
            PaymentContractServiceRequestBody paymentRequestBody
    ) {
        InvoiceDomain invoiceDomain = new InvoiceDomain();

        invoiceDomain.setDocumentId(paymentRequestBody.getInvoiceIdentifyDocument());
        invoiceDomain.setFullName(paymentRequestBody.getInvoiceFullName());
        invoiceDomain.setInvoiceDate(LocalDate.now());

        invoiceDomain.setInvoiceDetailList(contractDomain.getServiceDomainList()
                .stream()
                .map(serviceDomain -> {
                    InvoiceDetailDomain invoiceDetailDomain = new InvoiceDetailDomain();

                    invoiceDetailDomain.setServiceId(serviceDomain.getServiceId());
                    invoiceDetailDomain.setServiceName(serviceDomain.getDescription());
                    invoiceDetailDomain.setUnitPrice(serviceDomain.getPrice().getAmount());
                    invoiceDetailDomain.setQuantity(serviceDomain.getQuantity().getValue());
                    invoiceDetailDomain.setUnitPrice(serviceDomain.getPrice().getAmount());
                    invoiceDetailDomain.setTotalAmount(serviceDomain
                            .getPrice()
                            .getAmount()
                            .multiply(
                                    BigDecimal.valueOf(serviceDomain
                                            .getQuantity()
                                            .getValue())));

                    return invoiceDetailDomain;
                }).toList());

        return invoiceDomain;
    }

    private PaymentDomain preparePayment(InvoiceDomain invoiceDomain) {
        PaymentDomain paymentDomain = new PaymentDomain();

        BigDecimal calculationOfTotal = invoiceDomain.getInvoiceDetailList()
                .stream()
                .map(InvoiceDetailDomain::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Money total = new Money(calculationOfTotal);
        paymentDomain.setTotalPrice(total);

        return paymentDomain;
    }

}
