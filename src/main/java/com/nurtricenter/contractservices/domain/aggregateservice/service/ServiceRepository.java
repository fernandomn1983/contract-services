package com.nurtricenter.contractservices.domain.aggregateservice.service;

import java.util.UUID;

public interface ServiceRepository {

    ServiceDomain getService(UUID serviceId);

}
