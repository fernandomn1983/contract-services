package com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ContractServiceId implements Serializable {

    private Integer contractId;
    private UUID serviceId;

}
