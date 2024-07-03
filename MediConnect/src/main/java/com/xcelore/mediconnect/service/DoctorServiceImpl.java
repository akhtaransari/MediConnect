package com.xcelore.mediconnect.service;

import com.xcelore.mediconnect.entity.Doctor;
import com.xcelore.mediconnect.enums.Speciality;
import com.xcelore.mediconnect.exception.MediConnectException;
import com.xcelore.mediconnect.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing doctors.
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Adds a new doctor to the database.
     *
     * @param doctor The doctor object to be saved.
     * @return The saved doctor object.
     */
    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    /**
     * Deletes a doctor from the database based on the given ID.
     *
     * @param id The ID of the doctor to be deleted.
     * @throws MediConnectException If doctor with given ID is not found.
     */
    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new MediConnectException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    /**
     * Retrieves a list of doctors based on the given city and specialty.
     *
     * @param city       The city where the doctors are located.
     * @param speciality The specialty of doctors to retrieve.
     * @return A list of doctors matching the given city and specialty.
     * @throws MediConnectException If no doctors are found for the given city and specialty.
     */
    @Override
    public List<Doctor> suggestDoctors(String city, Speciality speciality) {
        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality(city, speciality);
        if (doctors.isEmpty()) {
            String message = "No doctors found in " + city + " with specialty " + speciality;
            if (!isValidCity(city)) {
                message = "We are still waiting to expand to your location";
            }
            throw new MediConnectException(message);
        }
        return doctors;
    }


    /**
     * Checks if the given city is one of the supported cities.
     *
     * @param city The city to check.
     * @return True if the city is supported (Delhi, Noida, Faridabad), false otherwise.
     */
    private boolean isValidCity(String city) {
        return "Delhi".equalsIgnoreCase(city) || "Noida".equalsIgnoreCase(city) || "Faridabad".equalsIgnoreCase(city);
    }
}
