package com.nurtricenter.contractservices.application.messaging.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InboundConstant {

    public static final String SERVICE_INBOUND_VALUE = "contract";
    public static final String OPERATION_SYNC_PATIENT_VALUE = "sync-patient";
    public static final String OPERATION_SYNC_SERVICE_VALUE = "sync-service";
    public static final String VERB_INBOUND_VALUE = "upsert";

}
