package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OutboundPatientPayloadDto {

    @JsonProperty("patientId")
    private UUID patientId;

}
