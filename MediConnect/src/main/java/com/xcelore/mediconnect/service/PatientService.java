package com.xcelore.mediconnect.service;

import com.xcelore.mediconnect.entity.Patient;

/**
 * Service interface for managing patients.
 */
public interface PatientService {

    /**
     * Adds a new patient.
     *
     * @param patient The patient object to be added.
     * @return The added patient object.
     */
    Patient addPatient(Patient patient);

    /**
     * Deletes a patient based on the given ID.
     *
     * @param id The ID of the patient to be deleted.
     */
    void deletePatient(Long id);

    /**
     * Retrieves a patient based on the given ID.
     *
     * @param id The ID of the patient to retrieve.
     * @return The patient object if found, otherwise null.
     */
    Patient getPatientById(Long id);
}
