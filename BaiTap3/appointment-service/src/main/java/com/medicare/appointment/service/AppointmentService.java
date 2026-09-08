package com.medicare.appointment.service;

import com.medicare.appointment.dto.AppointmentDto;
import com.medicare.appointment.model.Appointment;
import com.medicare.appointment.model.AppointmentStatus;
import com.medicare.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Transactional(readOnly = true)
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch khám với ID: " + id));
        return mapToDto(appointment);
    }

    @Transactional
    public AppointmentDto createAppointment(AppointmentDto dto) {
        Appointment appointment = Appointment.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentTime(dto.getAppointmentTime())
                .status(dto.getStatus() != null ? dto.getStatus() : AppointmentStatus.PENDING)
                .reason(dto.getReason())
                .build();
        Appointment saved = appointmentRepository.save(appointment);
        return mapToDto(saved);
    }

    @Transactional
    public AppointmentDto updateAppointment(Long id, AppointmentDto dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch khám với ID: " + id));

        appointment.setPatientId(dto.getPatientId());
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        if (dto.getStatus() != null) {
            appointment.setStatus(dto.getStatus());
        }
        appointment.setReason(dto.getReason());

        Appointment updated = appointmentRepository.save(appointment);
        return mapToDto(updated);
    }

    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy lịch khám với ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    private AppointmentDto mapToDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentTime(appointment.getAppointmentTime())
                .status(appointment.getStatus())
                .reason(appointment.getReason())
                .build();
    }
}
