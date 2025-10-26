package com.nurtricenter.contractservices.domain.service;

import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.domain.valueobjects.Quantity;

import java.util.UUID;

public class ServiceDomain {

    private UUID serviceId;
    private String description;
    private Money price;
    private Quantity quantity;

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

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

}
