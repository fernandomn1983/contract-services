package com.nurtricenter.contractservices.unittest.application.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nurtricenter.contractservices.application.mapper.ServiceMapper;
import com.nurtricenter.contractservices.application.repository.ServiceRepositoryImpl;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.service.ServiceRepository;
import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ServiceRepositoryJpa;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Tag("unit")
public class ServiceRepositoryImplTest {

    private static ServiceRepository sut;

    private static ServiceRepositoryJpa serviceRepositoryJpa;
    private static ServiceMapper serviceMapper;

    @BeforeAll
    static void setUpClass() {
        serviceRepositoryJpa = mock(ServiceRepositoryJpa.class);
        serviceMapper = mock(ServiceMapper.class);

        sut = new ServiceRepositoryImpl(serviceRepositoryJpa, serviceMapper);
    }

    @Test
    void upsertService_whenSendingCorrectValues_upsertsSuccessfully() {
        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setServiceId(UUID.fromString("d8864111-02ed-4835-94f0-91addf0f0ad1"));
        serviceDomain.setQuantity(new Quantity(10));
        serviceDomain.setPrice(new Money(new BigDecimal("100.00")));

        when(serviceMapper.toServiceJpaEntity(serviceDomain)).thenReturn(new ServiceEntityJpa());
        when(serviceRepositoryJpa.save(Mockito.any(ServiceEntityJpa.class)))
                .thenReturn(new ServiceEntityJpa());
        when(serviceMapper.toServiceDomain(Mockito.any(ServiceEntityJpa.class)))
                .thenReturn(serviceDomain);

        ServiceDomain result = sut.upsertService(serviceDomain);

        assertNotNull(result);
    }
}
