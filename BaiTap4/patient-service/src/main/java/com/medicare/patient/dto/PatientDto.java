package com.medicare.patient.dto;

import com.medicare.patient.model.Patient.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private String insuranceId;
}
