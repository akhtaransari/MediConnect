package com.xcelore.mediconnect;

import com.xcelore.mediconnect.entity.Doctor;
import com.xcelore.mediconnect.entity.Patient;
import com.xcelore.mediconnect.enums.Speciality;
import com.xcelore.mediconnect.repository.DoctorRepository;
import com.xcelore.mediconnect.repository.PatientRepository;
import com.xcelore.mediconnect.service.DoctorServiceImpl;
import com.xcelore.mediconnect.service.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for MediConnect application services.
 */
public class MediConnectApplicationTests {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @InjectMocks
    private PatientServiceImpl patientService;

    /**
     * Setup method to initialize Mockito annotations.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for adding a doctor.
     */
    @Test
    public void testAddDoctor() {
        // Create a doctor object for testing
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Rahul Sharma");
        doctor.setEmail("rahul.sharma@example.com");
        doctor.setPhoneNumber("+91-9876543210");
        doctor.setCity("Delhi");
        doctor.setSpeciality(Speciality.DERMATOLOGY);

        // Mock the behavior of doctorRepository.save(doctor)
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        // Call the service method to add the doctor
        Doctor savedDoctor = doctorService.addDoctor(doctor);

        // Verify the doctorRepository.save method was called once
        Mockito.verify(doctorRepository, Mockito.times(1)).save(doctor);

        // Assert that the returned doctor object is not null
        Assertions.assertNotNull(savedDoctor);

        // Assert other conditions if needed
        Assertions.assertEquals(doctor.getId(), savedDoctor.getId());
        Assertions.assertEquals(doctor.getName(), savedDoctor.getName());
        Assertions.assertEquals(doctor.getEmail(), savedDoctor.getEmail());
        Assertions.assertEquals(doctor.getPhoneNumber(), savedDoctor.getPhoneNumber());
        Assertions.assertEquals(doctor.getCity(), savedDoctor.getCity());
        Assertions.assertEquals(doctor.getSpeciality(), savedDoctor.getSpeciality());
    }


    /**
     * Test case for suggesting doctors based on city and speciality.
     */
    @Test
    public void testSuggestDoctors() {
        // Create test data
        String city = "Delhi";
        Speciality speciality = Speciality.DERMATOLOGY;
        List<Doctor> doctors = new ArrayList<>();
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setName("Dr. Rahul Sharma");
        doctor1.setEmail("rahul.sharma@example.com");
        doctor1.setPhoneNumber("+91-9876543210");
        doctor1.setCity(city);
        doctor1.setSpeciality(speciality);
        doctors.add(doctor1);

        // Mock the behavior of doctorRepository.findByCityAndSpeciality(city, speciality)
        when(doctorRepository.findByCityAndSpeciality(city, speciality)).thenReturn(doctors);

        // Call the service method to suggest doctors
        List<Doctor> suggestedDoctors = doctorService.suggestDoctors(city, speciality);

        // Verify the doctorRepository.findByCityAndSpeciality method was called once with the correct parameters
        Mockito.verify(doctorRepository, Mockito.times(1)).findByCityAndSpeciality(city, speciality);

        // Assert that the returned list is not null and contains the expected doctor
        Assertions.assertNotNull(suggestedDoctors);
        Assertions.assertFalse(suggestedDoctors.isEmpty());
        Assertions.assertEquals(1, suggestedDoctors.size());
        Doctor suggestedDoctor = suggestedDoctors.get(0);
        Assertions.assertEquals(doctor1.getId(), suggestedDoctor.getId());
        Assertions.assertEquals(doctor1.getName(), suggestedDoctor.getName());
        Assertions.assertEquals(doctor1.getEmail(), suggestedDoctor.getEmail());
        Assertions.assertEquals(doctor1.getPhoneNumber(), suggestedDoctor.getPhoneNumber());
        Assertions.assertEquals(doctor1.getCity(), suggestedDoctor.getCity());
        Assertions.assertEquals(doctor1.getSpeciality(), suggestedDoctor.getSpeciality());
    }

    /**
     * Test case for adding a patient.
     */
    @Test
    public void testAddPatient() {
        // Create a patient object for testing
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Rameshwari Devi"); // Indian name example
        patient.setEmail("rameshwari.devi@example.com");
        patient.setPhoneNumber("+91-8765432109"); // Indian phone number format
        patient.setCity("Mumbai");

        // Mock the behavior of patientRepository.save(patient)
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Call the service method to add the patient
        Patient savedPatient = patientService.addPatient(patient);

        // Verify the patientRepository.save method was called once
        Mockito.verify(patientRepository, Mockito.times(1)).save(patient);

        // Assert that the returned patient object is not null
        Assertions.assertNotNull(savedPatient);

        // Assert other conditions if needed
        Assertions.assertEquals(patient.getId(), savedPatient.getId());
        Assertions.assertEquals(patient.getName(), savedPatient.getName());
        Assertions.assertEquals(patient.getEmail(), savedPatient.getEmail());
        Assertions.assertEquals(patient.getPhoneNumber(), savedPatient.getPhoneNumber());
        Assertions.assertEquals(patient.getCity(), savedPatient.getCity());
    }


    /**
     * Test case for retrieving a patient by ID.
     */
    @Test
    public void testGetPatientById() {
        // Create a patient object for testing
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Rameshwari Devi");
        patient.setEmail("rameshwari.devi@example.com");
        patient.setPhoneNumber("+91-8765432109");
        patient.setCity("Mumbai");

        // Mock the behavior of patientRepository.findById(id)
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Call the service method to get the patient by ID
        Patient retrievedPatient = patientService.getPatientById(1L);

        // Verify the patientRepository.findById method was called once with the correct ID
        Mockito.verify(patientRepository, Mockito.times(1)).findById(1L);

        // Assert that the returned patient object is not null and matches the expected patient
        Assertions.assertNotNull(retrievedPatient);
        Assertions.assertEquals(patient.getId(), retrievedPatient.getId());
        Assertions.assertEquals(patient.getName(), retrievedPatient.getName());
        Assertions.assertEquals(patient.getEmail(), retrievedPatient.getEmail());
        Assertions.assertEquals(patient.getPhoneNumber(), retrievedPatient.getPhoneNumber());
        Assertions.assertEquals(patient.getCity(), retrievedPatient.getCity());
    }

}
