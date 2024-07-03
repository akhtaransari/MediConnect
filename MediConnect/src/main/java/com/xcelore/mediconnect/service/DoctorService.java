package com.xcelore.mediconnect.service;

import com.xcelore.mediconnect.entity.Doctor;
import com.xcelore.mediconnect.enums.Speciality;

import java.util.List;

/**
 * Service interface for managing doctors.
 */
public interface DoctorService {

    /**
     * Adds a new doctor.
     *
     * @param doctor The doctor object to be added.
     * @return The added doctor object.
     */
    Doctor addDoctor(Doctor doctor);

    /**
     * Deletes a doctor based on the given ID.
     *
     * @param id The ID of the doctor to be deleted.
     */
    void deleteDoctor(Long id);

    /**
     * Suggests doctors based on the given city and specialty.
     *
     * @param city The city where the patient is located.
     * @param speciality The specialty of doctors to suggest.
     * @return A list of doctors matching the given criteria.
     */
    List<Doctor> suggestDoctors(String city, Speciality speciality);

}
