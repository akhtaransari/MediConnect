package com.xcelore.mediconnect.controller;

import com.xcelore.mediconnect.entity.Doctor;
import com.xcelore.mediconnect.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling doctor-related HTTP requests.
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService; // Autowired DoctorService for handling doctor operations

    /**
     * Endpoint to add a new doctor.
     *
     * @param doctor The doctor object to be added, validated using @Valid.
     * @return ResponseEntity with the added doctor object and HTTP status 200 (OK).
     */
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody @Valid Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    /**
     * Endpoint to delete a doctor by ID.
     *
     * @param id The ID of the doctor to be deleted.
     * @return ResponseEntity with HTTP status 204 (NO_CONTENT) if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
