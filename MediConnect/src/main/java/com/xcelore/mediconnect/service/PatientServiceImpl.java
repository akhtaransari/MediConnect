package com.xcelore.mediconnect.service;

import com.xcelore.mediconnect.entity.Patient;
import com.xcelore.mediconnect.exception.MediConnectException;
import com.xcelore.mediconnect.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing patients.
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Adds a new patient to the database.
     *
     * @param patient The patient object to be saved.
     * @return The saved patient object.
     */
    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Deletes a patient from the database based on the given ID.
     *
     * @param id The ID of the patient to be deleted.
     * @throws MediConnectException If patient with given ID is not found.
     */
    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new MediConnectException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    /**
     * Retrieves a patient from the database based on the given ID.
     *
     * @param id The ID of the patient to retrieve.
     * @return The patient object if found.
     * @throws MediConnectException If patient with given ID is not found.
     */
    @Override
    public Patient getPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElseThrow(() -> new MediConnectException("Patient not found with id: " + id));
    }
}
