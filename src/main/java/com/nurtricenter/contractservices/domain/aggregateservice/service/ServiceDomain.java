package com.nurtricenter.contractservices.domain.aggregateservice.service;

import com.nurtricenter.contractservices.domain.shared.valueobjects.Money;

import java.util.UUID;

public class ServiceDomain {

    private UUID serviceId;
    private String description;
    private Money price;

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

}
