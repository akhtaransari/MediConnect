package com.xcelore.mediconnect.controller;

import com.xcelore.mediconnect.entity.Patient;
import com.xcelore.mediconnect.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling patient-related HTTP requests.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService; // Autowired PatientService for handling patient operations

    /**
     * Endpoint to add a new patient.
     *
     * @param patient The patient object to be added, validated using @Valid.
     * @return ResponseEntity with the added patient object and HTTP status 200 (OK).
     */
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        return ResponseEntity.ok(patientService.addPatient(patient));
    }

    /**
     * Endpoint to delete a patient by ID.
     *
     * @param id The ID of the patient to be deleted.
     * @return ResponseEntity with HTTP status 204 (NO_CONTENT) if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
