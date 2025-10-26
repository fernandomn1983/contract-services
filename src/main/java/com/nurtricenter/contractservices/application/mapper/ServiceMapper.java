package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
public interface ServiceMapper {

    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "cost", target = "price")
    ServiceDomain toDomain(ServiceEntityJpa serviceEntityJpa);

    @Mapping(source = "serviceId.id", target = "serviceId")
    @Mapping(source = "serviceId.description", target = "description")
    @Mapping(source = "quantity", target = "quantity", qualifiedByName = "mapQuantity")
    ServiceDomain toDomain(ContractServiceEntityJpa contractServiceEntityJpa);

    @Named("mapQuantity")
    default Quantity mapToQuantity(int quantity) {
        return new Quantity(quantity);
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
