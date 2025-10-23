package com.nurtricenter.contractservices.domain.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ServiceRepository {

    ServiceDomain getService(UUID serviceId); //TODO: delete?
    List<ServiceDomain> getServicesByIds(Set<UUID> serviceIds);

}
