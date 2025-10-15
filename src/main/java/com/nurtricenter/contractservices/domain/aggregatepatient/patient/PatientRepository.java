package com.nurtricenter.contractservices.domain.aggregatepatient.patient;

import java.util.UUID;

public interface PatientRepository {

    Patient getPatient(UUID patientId);

}
