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
    ServiceDomain toDomain(ContractServiceEntityJpa contractServiceEntityJpa);

    @Named("toQuantity")
    default Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }

    @Named("toMoney")
    default Money toMoney(BigDecimal amount) {
        return new Money(amount);
    }

    List<ServiceDomain> toDomainList(List<ServiceEntityJpa> serviceEntityJpaList);

    @Mapping(source = "serviceId", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price.amount", target = "cost")
    ServiceEntityJpa toJpaEntity(ServiceDomain serviceDomain);

    default UUID map(ServiceEntityJpa serviceEntityJpa) {
        return serviceEntityJpa.getId();
    }

    default ServiceEntityJpa map(UUID value) {
        ServiceEntityJpa serviceEntityJpa = new ServiceEntityJpa();
        serviceEntityJpa.setId(value);

        return serviceEntityJpa;
    }

}
