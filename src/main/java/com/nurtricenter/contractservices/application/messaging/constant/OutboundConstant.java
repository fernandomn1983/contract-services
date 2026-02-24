package com.nurtricenter.contractservices.application.messaging.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OutboundConstant {

    public static final String PATIENT_SERVICE_OUTBOUND_TARGET_VALUE = "nutritional-control";
    public static final String PATIENT_OPERATION_OUTBOUND_VALUE = "sync-patient";
    public static final String PATIENT_VERB_OUTBOUND_VALUE = "confirming";
    public static final String OUTBOUND_TOPIC = "${kafka.topics.outbound}";
    public static final String PATIENT_AGGREGATE_TYPE = "PatientSync";

}
