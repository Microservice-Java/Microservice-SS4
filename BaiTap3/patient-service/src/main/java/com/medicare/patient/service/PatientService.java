package com.medicare.patient.service;

import com.medicare.patient.dto.PatientDto;
import com.medicare.patient.model.Patient;
import com.medicare.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id));
        return mapToDto(patient);
    }

    @Transactional
    public PatientDto createPatient(PatientDto dto) {
        Patient patient = Patient.builder()
                .fullName(dto.getFullName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .insuranceId(dto.getInsuranceId())
                .build();
        Patient saved = patientRepository.save(patient);
        return mapToDto(saved);
    }

    @Transactional
    public PatientDto updatePatient(Long id, PatientDto dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id));

        patient.setFullName(dto.getFullName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setInsuranceId(dto.getInsuranceId());

        Patient updated = patientRepository.save(patient);
        return mapToDto(updated);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id);
        }
        patientRepository.deleteById(id);
    }

    private PatientDto mapToDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .address(patient.getAddress())
                .insuranceId(patient.getInsuranceId())
                .build();
    }
}
