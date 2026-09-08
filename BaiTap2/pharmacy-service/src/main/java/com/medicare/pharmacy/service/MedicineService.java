package com.medicare.pharmacy.service;

import com.medicare.pharmacy.dto.MedicineDto;
import com.medicare.pharmacy.model.Medicine;
import com.medicare.pharmacy.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public List<MedicineDto> getAllMedicines() {
        return medicineRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MedicineDto getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        return mapToDto(medicine);
    }

    public MedicineDto createMedicine(MedicineDto dto) {
        Medicine medicine = mapToEntity(dto);
        Medicine saved = medicineRepository.save(medicine);
        return mapToDto(saved);
    }

    public MedicineDto updateMedicine(Long id, MedicineDto dto) {
        Medicine existing = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));

        existing.setName(dto.getName());
        existing.setCategory(dto.getCategory());
        existing.setPrice(dto.getPrice());
        existing.setStockQuantity(dto.getStockQuantity());
        existing.setManufacturer(dto.getManufacturer());
        existing.setExpiryDate(dto.getExpiryDate());

        Medicine updated = medicineRepository.save(existing);
        return mapToDto(updated);
    }

    public void deleteMedicine(Long id) {
        if (!medicineRepository.existsById(id)) {
            throw new RuntimeException("Medicine not found with id: " + id);
        }
        medicineRepository.deleteById(id);
    }

    private MedicineDto mapToDto(Medicine entity) {
        return MedicineDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .stockQuantity(entity.getStockQuantity())
                .manufacturer(entity.getManufacturer())
                .expiryDate(entity.getExpiryDate())
                .build();
    }

    private Medicine mapToEntity(MedicineDto dto) {
        return Medicine.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .manufacturer(dto.getManufacturer())
                .expiryDate(dto.getExpiryDate())
                .build();
    }
}
