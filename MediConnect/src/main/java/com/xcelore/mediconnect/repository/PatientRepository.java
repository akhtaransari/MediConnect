package com.xcelore.mediconnect.repository;

import com.xcelore.mediconnect.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing patients using JpaRepository.
 * Handles database operations for Patient entities.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
