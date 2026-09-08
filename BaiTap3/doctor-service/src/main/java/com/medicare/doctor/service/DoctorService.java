package com.medicare.doctor.service;

import com.medicare.doctor.dto.DoctorDto;
import com.medicare.doctor.model.Doctor;
import com.medicare.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ với ID: " + id));
        return mapToDto(doctor);
    }

    @Transactional
    public DoctorDto createDoctor(DoctorDto dto) {
        Doctor doctor = Doctor.builder()
                .fullName(dto.getFullName())
                .specialty(dto.getSpecialty())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .scheduleInfo(dto.getScheduleInfo())
                .build();
        Doctor saved = doctorRepository.save(doctor);
        return mapToDto(saved);
    }

    @Transactional
    public DoctorDto updateDoctor(Long id, DoctorDto dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ với ID: " + id));

        doctor.setFullName(dto.getFullName());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setPhone(dto.getPhone());
        doctor.setEmail(dto.getEmail());
        doctor.setScheduleInfo(dto.getScheduleInfo());

        Doctor updated = doctorRepository.save(doctor);
        return mapToDto(updated);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bác sĩ với ID: " + id);
        }
        doctorRepository.deleteById(id);
    }

    private DoctorDto mapToDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .specialty(doctor.getSpecialty())
                .phone(doctor.getPhone())
                .email(doctor.getEmail())
                .scheduleInfo(doctor.getScheduleInfo())
                .build();
    }
}
