package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.invoice.InvoiceDomain;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.InvoiceBatchEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.InvoiceEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    @Mapping(target = "identityDocument", source = "documentId")
    @Mapping(target = "fullName", source = "fullName")
    InvoiceEntityJpa toEntityJpa(InvoiceDomain invoiceDomain);

    @Mapping(target = "documentId", source = "identityDocument")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "invoiceNumber", source = "invoiceNumber")
    @Mapping(target = "invoiceDate", source = "invoiceDate")
    @Mapping(target = "limitDate", source = "invoiceBatchEntityJpa", qualifiedByName = "getLimitDate")
    InvoiceDomain toDomain(InvoiceEntityJpa invoiceEntity);

    @Named("getLimitDate")
    default LocalDate getLimitDate(InvoiceBatchEntityJpa invoiceBatchEntityJpa) {
        return invoiceBatchEntityJpa.getInvoiceBatchLimitDate().toLocalDate();
    }
    
}
