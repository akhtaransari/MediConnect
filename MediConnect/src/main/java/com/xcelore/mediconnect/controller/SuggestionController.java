package com.xcelore.mediconnect.controller;

import com.xcelore.mediconnect.entity.Doctor;
import com.xcelore.mediconnect.entity.Patient;
import com.xcelore.mediconnect.enums.Speciality;
import com.xcelore.mediconnect.enums.Symptom;
import com.xcelore.mediconnect.service.DoctorService;
import com.xcelore.mediconnect.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling suggestions related to doctors based on patient symptoms.
 */
@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {

    @Autowired
    private DoctorService doctorService; // Autowired DoctorService for handling doctor operations

    @Autowired
    private PatientService patientService; // Autowired PatientService for handling patient operations

    /**
     * Endpoint to suggest doctors based on a patient's symptom.
     *
     * @param patientId The ID of the patient for whom doctors are suggested.
     * @return ResponseEntity containing a list of suggested doctors or an error message if no doctors are found.
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<?> suggestDoctors(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }

        // Map patient's symptom to corresponding doctor speciality
        Speciality speciality = mapSymptomToSpeciality(patient.getSymptom());

        // Get doctors based on patient's city and mapped speciality
        List<Doctor> doctors = doctorService.suggestDoctors(patient.getCity(), speciality);
        if (doctors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There isn’t any doctor present at your location for your symptom");
        }
        return ResponseEntity.ok(doctors);
    }

    /**
     * Helper method to map patient symptoms to doctor specialities.
     *
     * @param symptom The symptom of the patient.
     * @return The corresponding speciality of doctors based on the symptom.
     * @throws IllegalArgumentException if the symptom does not match any known speciality.
     */
    private Speciality mapSymptomToSpeciality(Symptom symptom) {
        switch (symptom) {
            case ARTHRITIS:
            case BACK_PAIN:
            case TISSUE_INJURIES:
                return Speciality.ORTHOPAEDIC;
            case DYSMENORRHEA:
                return Speciality.GYNECOLOGY;
            case SKIN_INFECTION:
            case SKIN_BURN:
                return Speciality.DERMATOLOGY;
            case EAR_PAIN:
                return Speciality.ENT;
            default:
                throw new IllegalArgumentException("Unknown symptom: " + symptom);
        }
    }
}
