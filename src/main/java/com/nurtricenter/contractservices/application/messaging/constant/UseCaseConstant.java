package com.nurtricenter.contractservices.application.messaging.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UseCaseConstant {

    public static final String PROCESSING_PATIENT_UPSERT_OPERATION_LOG = "Processing patient upsert operation. transactionId = {}, patientId = {}";
    public static final String PATIENT_UPSERT_OPERATION_PROCESSED_LOG = "Patient upsert operation processed. transactionId = {}, patientId = {}";
    public static final String ERROR_PROCESSING_PATIENT_UPSERT_OPERATION_LOG = "Error processing patient upsert operation. transactionId = {}, patientId = {}, error = {}";

    public static final String PROCESSING_SERVICE_UPSERT_OPERATION_LOG = "Processing service upsert operation. transactionId = {}, serviceId = {}";
    public static final String SERVICE_UPSERT_OPERATION_PROCESSED_LOG = "Service upsert operation processed. transactionId = {}, serviceId = {}";
    public static final String ERROR_PROCESSING_SERVICE_UPSERT_OPERATION_LOG = "Error processing service upsert operation. transactionId = {}, serviceId = {}, error = {}";

    public static final String UNKNOWN_REQUEST_LOG = "Unknown request. transactionId = {}, service = {}, operation = {}, verb = {}";

}
