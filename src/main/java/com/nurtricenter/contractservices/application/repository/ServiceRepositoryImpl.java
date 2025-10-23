package com.nurtricenter.contractservices.application.repository;

import com.nurtricenter.contractservices.application.mapper.ServiceMapper;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.service.ServiceRepository;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ServiceRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceRepositoryJpa serviceRepositoryJpa;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceDomain getService(UUID serviceId) {
        Optional<ServiceEntityJpa> optionalServiceEntityJpa = serviceRepositoryJpa.findById(serviceId);

        return optionalServiceEntityJpa.map(serviceMapper::toDomain).
                orElse(null);

    }

    @Override
    public List<ServiceDomain> getServicesByIds(Set<UUID> serviceIds) {
        List<ServiceEntityJpa> serviceEntityJpaList = serviceRepositoryJpa.findAllById(serviceIds);

        return serviceEntityJpaList.stream()
                .map(serviceMapper::toDomain)
                .toList();
    }

}
