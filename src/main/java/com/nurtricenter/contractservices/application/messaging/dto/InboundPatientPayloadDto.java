package com.nurtricenter.contractservices.application.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundPatientPayloadDto {

    @JsonProperty("id")
    private UUID patientId;

    @JsonProperty("fullName")
    private String patientName;

    @JsonProperty("lastName")
    private String patientLastName;

    @JsonProperty("identityCard")
    private String patientIdentityDocument;
}
