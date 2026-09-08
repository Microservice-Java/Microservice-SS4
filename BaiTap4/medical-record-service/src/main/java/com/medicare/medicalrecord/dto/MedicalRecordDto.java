package com.medicare.medicalrecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private String treatmentPlan;
    private LocalDateTime recordDate;
    private String notes;
}
