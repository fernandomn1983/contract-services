package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.valueobjects.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nurtricenter.contractservices.domain.payment.PaymentDomain;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PaymentEntityJpa;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = {InvoiceMapper.class})
public interface PaymentMapper {

    @Mapping(source = "id", target = "paymentId")
    @Mapping(source = "totalCost", target = "totalPrice", qualifiedByName = "toMoney")
    @Mapping(source = "invoiceEntityJpa", target = "invoiceDomain")
    PaymentDomain toDomain(PaymentEntityJpa paymentEntityJpa);

    @Named("toMoney")
    default Money toMoney(BigDecimal amount) {
        return new Money(amount);
    }
    
}
