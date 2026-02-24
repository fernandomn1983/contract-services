package com.nurtricenter.contractservices.domain.patient;

public interface PatientRepository {

    PatientDomain upsertPatient(PatientDomain patientDomain);

}
