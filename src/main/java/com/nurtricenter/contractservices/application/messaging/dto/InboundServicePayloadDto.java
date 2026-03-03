package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundServicePayloadDto {

    @JsonProperty("serviceId")
    private UUID serviceId;

    @JsonProperty("serviceDescription")
    private String serviceDescription;

    @JsonProperty("serviceCost")
    private BigDecimal serviceCost;

}
