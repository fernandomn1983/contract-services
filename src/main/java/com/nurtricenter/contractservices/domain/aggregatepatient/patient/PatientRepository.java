package com.nurtricenter.contractservices.domain.aggregatepatient.patient;

public interface PatientRepository {

    PatientDomain getPatient(int patientId);

}
