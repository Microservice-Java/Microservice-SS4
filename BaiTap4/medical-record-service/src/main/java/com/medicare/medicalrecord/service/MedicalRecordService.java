package com.medicare.medicalrecord.service;

import com.medicare.medicalrecord.dto.MedicalRecordDto;
import com.medicare.medicalrecord.model.MedicalRecord;
import com.medicare.medicalrecord.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecordDto> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MedicalRecordDto getMedicalRecordById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));
        return mapToDto(record);
    }

    public MedicalRecordDto createMedicalRecord(MedicalRecordDto dto) {
        MedicalRecord record = mapToEntity(dto);
        if (record.getRecordDate() == null) {
            record.setRecordDate(LocalDateTime.now());
        }
        MedicalRecord saved = medicalRecordRepository.save(record);
        return mapToDto(saved);
    }

    public MedicalRecordDto updateMedicalRecord(Long id, MedicalRecordDto dto) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));

        existing.setPatientId(dto.getPatientId());
        existing.setDoctorId(dto.getDoctorId());
        existing.setDiagnosis(dto.getDiagnosis());
        existing.setTreatmentPlan(dto.getTreatmentPlan());
        if (dto.getRecordDate() != null) {
            existing.setRecordDate(dto.getRecordDate());
        }
        existing.setNotes(dto.getNotes());

        MedicalRecord updated = medicalRecordRepository.save(existing);
        return mapToDto(updated);
    }

    public void deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new RuntimeException("Medical record not found with id: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecordDto mapToDto(MedicalRecord entity) {
        return MedicalRecordDto.builder()
                .id(entity.getId())
                .patientId(entity.getPatientId())
                .doctorId(entity.getDoctorId())
                .diagnosis(entity.getDiagnosis())
                .treatmentPlan(entity.getTreatmentPlan())
                .recordDate(entity.getRecordDate())
                .notes(entity.getNotes())
                .build();
    }

    private MedicalRecord mapToEntity(MedicalRecordDto dto) {
        return MedicalRecord.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .diagnosis(dto.getDiagnosis())
                .treatmentPlan(dto.getTreatmentPlan())
                .recordDate(dto.getRecordDate())
                .notes(dto.getNotes())
                .build();
    }
}
