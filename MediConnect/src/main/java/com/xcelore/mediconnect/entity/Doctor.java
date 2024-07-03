package com.xcelore.mediconnect.entity;

import com.xcelore.mediconnect.enums.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Entity class representing a doctor.
 */
@Entity
@Table(name = "doctors")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name; // Name of the doctor

    @NotBlank
    @Email
    private String email; // Email address of the doctor

    @NotBlank
    @Size(min = 10, max = 15)
    private String phoneNumber; // Phone number of the doctor

    @NotBlank
    @Size(max = 20)
    private String city; // City where the doctor practices

    @Enumerated(EnumType.STRING)
    private Speciality speciality; // Speciality of the doctor
}
