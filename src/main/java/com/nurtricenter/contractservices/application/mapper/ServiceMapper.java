package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.aggregateservice.service.ServiceDomain;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
public interface ServiceMapper {

    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "cost", target = "price")
    ServiceDomain toDomain(ServiceEntityJpa serviceEntityJpa);

}
