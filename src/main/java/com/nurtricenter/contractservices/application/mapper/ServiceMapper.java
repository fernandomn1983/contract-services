package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
public interface ServiceMapper {

    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "cost", target = "price")
    ServiceDomain toDomain(ServiceEntityJpa serviceEntityJpa);

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
