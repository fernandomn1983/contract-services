package com.nurtricenter.contractservices.domain.aggregateservice.service;

import java.util.UUID;

public interface ServiceRepository {

    Service getService(UUID serviceId);

}
