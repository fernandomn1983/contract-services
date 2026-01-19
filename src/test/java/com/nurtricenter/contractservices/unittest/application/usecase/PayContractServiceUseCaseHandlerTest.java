package com.nurtricenter.contractservices.unittest.application.usecase;

import com.nurtricenter.contractservices.application.repository.ContractRepositoryImpl;
import com.nurtricenter.contractservices.application.repository.PaymentRepositoryImpl;
import com.nurtricenter.contractservices.application.usecase.PayContractServiceUseCaseCommand;
import com.nurtricenter.contractservices.application.usecase.PayContractServiceUseCaseHandler;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.domain.patient.PatientDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentDomain;
import com.nurtricenter.contractservices.domain.payment.PaymentRepository;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.domain.valueobjects.Status;
import com.nurtricenter.contractservices.presentation.dto.PaymentContractServiceRequestBody;
import com.nurtricenter.contractservices.presentation.mapper.PaymentContractServiceMapper;
import com.nurtricenter.contractservices.shared.value.MetadataValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")
class PayContractServiceUseCaseHandlerTest {

    private static PayContractServiceUseCaseHandler sut;

    private static ContractRepository contractRepository;
    private static PaymentRepository paymentRepository;
    private static MetadataValues metadataValues;
    private static PaymentContractServiceMapper paymentContractServiceMapper;

    @BeforeAll
    static void setUp() {
        contractRepository = mock(ContractRepositoryImpl.class);
        paymentRepository = mock(PaymentRepositoryImpl.class);
        metadataValues = mock(MetadataValues.class);
        paymentContractServiceMapper = mock(PaymentContractServiceMapper.class);

        sut = new PayContractServiceUseCaseHandler(
                contractRepository,
                paymentRepository,
                metadataValues,
                paymentContractServiceMapper);
    }

    @Test
    void payContract_whenPreparedContractExist_contractIsPaidSuccessfully() {
        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setContractId(1);
        contractDomain.setContractStartDate(LocalDateTime.of(2026, 1, 2, 0, 0));
        contractDomain.setContractEndDate(LocalDateTime.of(2026, 2, 2, 0, 0));
        contractDomain.setContractStatus(Status.PREPARED);

        PatientDomain patientDomain = new PatientDomain();
        patientDomain.setPatientId("2ef33ff3-7360-4abe-8dfd-d27f5c2fd8d8");
        patientDomain.setFirstName("John");
        patientDomain.setLastName("Doe");

        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setServiceId(UUID.fromString("0443cafd-1a75-4fc4-a12f-cf11cbc6062a"));
        serviceDomain.setQuantity(new Quantity(2));
        serviceDomain.setPrice(new Money(new BigDecimal("50.0")));

        contractDomain.setPatientDomain(patientDomain);
        contractDomain.setServiceDomainList(List.of(serviceDomain));

        when(contractRepository.getContract(1)).thenReturn(contractDomain);

        PaymentContractServiceRequestBody paymentContractServiceRequestBody = new PaymentContractServiceRequestBody();
        paymentContractServiceRequestBody.setInvoiceFullName("John Doe");
        paymentContractServiceRequestBody.setInvoiceIdentifyDocument("5622431");

        InvoiceDomain invoiceDomain = new InvoiceDomain();
        invoiceDomain.setInvoiceId(1);

        PaymentDomain paymentDomain = new PaymentDomain();
        paymentDomain.setPaymentId(1);
        paymentDomain.setTotalPrice(new Money(new BigDecimal("100.0")));
        paymentDomain.setInvoiceDomain(invoiceDomain);

        when(paymentRepository.pay(any(PaymentDomain.class), any(ContractDomain.class))).thenReturn(paymentDomain);

        PayContractServiceUseCaseCommand command = new PayContractServiceUseCaseCommand(1, paymentContractServiceRequestBody);

        sut.handle(command);

        Assertions.assertEquals(Status.PAID, contractDomain.getContractStatus());
    }

}