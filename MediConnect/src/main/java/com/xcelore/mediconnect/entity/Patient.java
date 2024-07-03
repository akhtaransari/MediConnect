package com.xcelore.mediconnect.entity;

import com.xcelore.mediconnect.enums.Symptom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Entity class representing a patient.
 */
@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name; // Name of the patient


    @NotBlank
    @Email
    private String email; // Email address of the patient

    @NotBlank
    @Size(min = 10, max = 15)
    private String phoneNumber; // Phone number of the patient

    @NotBlank
    @Size(max = 20)
    private String city; // City where the patient resides

    @Enumerated(EnumType.STRING)
    private Symptom symptom; // Symptom experienced by the patient
}
