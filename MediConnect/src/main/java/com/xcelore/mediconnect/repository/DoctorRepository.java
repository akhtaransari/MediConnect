package com.xcelore.mediconnect.repository;

import com.xcelore.mediconnect.entity.Doctor;
import com.xcelore.mediconnect.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing doctors using JpaRepository.
 * Handles database operations for Doctor entities.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Retrieves a list of doctors based on the given city and specialty.
     *
     * @param city The city where the doctors are located.
     * @param speciality The specialty of doctors to retrieve.
     * @return A list of doctors matching the given city and specialty.
     */
    List<Doctor> findByCityAndSpeciality(String city, Speciality speciality);
}
