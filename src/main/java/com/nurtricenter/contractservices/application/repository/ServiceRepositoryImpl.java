package com.nurtricenter.contractservices.application.repository;

import com.nurtricenter.contractservices.application.mapper.ServiceMapper;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.service.ServiceRepository;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ServiceRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceRepositoryJpa serviceRepositoryJpa;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceDomain upsertService(ServiceDomain serviceDomain) {
        ServiceEntityJpa serviceEntityJpa = serviceMapper.toServiceJpaEntity(serviceDomain);
        ServiceEntityJpa serviceEntityJpaResult = serviceRepositoryJpa.save(serviceEntityJpa);

        return serviceMapper.toServiceDomain(serviceEntityJpaResult);
    }

}
