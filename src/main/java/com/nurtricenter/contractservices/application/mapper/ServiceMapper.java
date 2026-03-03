package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(source = "serviceId.id", target = "serviceId")
    @Mapping(source = "serviceId.description", target = "description")
    @Mapping(source = "serviceId.cost", target = "price", qualifiedByName = "toMoney")
    @Mapping(source = "quantity", target = "quantity", qualifiedByName = "toQuantity")
    ServiceDomain toServiceDomainFromContractEntityJpa(ContractServiceEntityJpa contractServiceEntityJpa);

    @Named("toQuantity")
    default Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }

    @Named("toMoney")
    default Money toMoney(BigDecimal amount) {
        return new Money(amount);
    }

    @Mapping(source = "serviceId", target = "serviceId.id")
    @Mapping(source = "description", target = "serviceId.description")
    @Mapping(source = "price", target = "serviceId.cost", qualifiedByName = "toCost")
    @Mapping(source = "quantity", target = "quantity", qualifiedByName = "toQuantityPrimitive")
    ContractServiceEntityJpa toContractEntityJpaFromServiceDomain(ServiceDomain serviceDomain);

    @Named("toCost")
    default BigDecimal toCost(Money money) {
        return money == null ? new BigDecimal(0) : money.getAmount();
    }

    @Named("toQuantityPrimitive")
    default int toQuantityPrimitive(Quantity quantity) {
        return quantity.getValue();
    }

    List<ServiceDomain> toServiceDomainList(List<ServiceEntityJpa> serviceEntityJpaList);

    @Mapping(source = "serviceId", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price.amount", target = "cost")
    ServiceEntityJpa toServiceJpaEntity(ServiceDomain serviceDomain);

    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "cost", target = "price", qualifiedByName = "toMoney")
    ServiceDomain toServiceDomain(ServiceEntityJpa serviceEntityJpa);

    default UUID map(ServiceEntityJpa serviceEntityJpa) {
        return serviceEntityJpa.getId();
    }

    default ServiceEntityJpa map(UUID value) {
        ServiceEntityJpa serviceEntityJpa = new ServiceEntityJpa();
        serviceEntityJpa.setId(value);

        return serviceEntityJpa;
    }

    List<ServiceEntityJpa> toServiceJpaList(List<ServiceDomain> serviceDomainList);

}
