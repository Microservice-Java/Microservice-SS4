# MediCare Hospital Microservices System - Entity Relationship Diagrams (ERD)

Tài liệu thiết kế sơ đồ ERD chi tiết cho 5 CSDL Microservices thuộc hệ thống Bệnh viện Đa khoa MediCare.

---

## 1. Patient Service ERD (`medicare_patient_db`)

```mermaid
erDiagram
    PATIENTS {
        BIGINT id PK "AUTO_INCREMENT"
        VARCHAR full_name "Họ và tên bệnh nhân"
        DATE date_of_birth "Ngày sinh"
        ENUM gender "MALE, FEMALE, OTHER"
        VARCHAR phone "Số điện thoại"
        VARCHAR address "Địa chỉ"
        VARCHAR insurance_id "Số BHYT"
        DATETIME created_at
        DATETIME updated_at
    }
```

---

## 2. Doctor Service ERD (`medicare_doctor_db`)

```mermaid
erDiagram
    DOCTORS {
        BIGINT id PK "AUTO_INCREMENT"
        VARCHAR full_name "Họ tên bác sĩ"
        VARCHAR specialty "Chuyên khoa (Nội, Ngoại, Nhi...)"
        VARCHAR phone "Số điện thoại liên hệ"
        VARCHAR email "Email làm việc"
        TEXT schedule_info "Thông tin lịch làm việc"
        DATETIME created_at
        DATETIME updated_at
    }
```

---

## 3. Appointment Service ERD (`medicare_appointment_db`)

```mermaid
erDiagram
    APPOINTMENTS {
        BIGINT id PK "AUTO_INCREMENT"
        BIGINT patient_id "Tham chiếu logic ID sang Patient Service"
        BIGINT doctor_id "Tham chiếu logic ID sang Doctor Service"
        DATETIME appointment_time "Thời gian hẹn khám"
        ENUM status "PENDING, CONFIRMED, COMPLETED, CANCELLED"
        VARCHAR reason "Lý do khám bệnh"
        DATETIME created_at
        DATETIME updated_at
    }
```

---

## 4. Medical Record Service ERD (`medicare_medical_record_db`)

```mermaid
erDiagram
    MEDICAL_RECORDS {
        BIGINT id PK "AUTO_INCREMENT"
        BIGINT patient_id "Tham chiếu logic ID sang Patient Service"
        BIGINT doctor_id "Tham chiếu logic ID sang Doctor Service"
        BIGINT appointment_id "Tham chiếu logic ID sang Appointment Service"
        TEXT diagnosis "Chẩn đoán y khoa"
        TEXT treatment_plan "Phác đồ điều trị"
        TEXT prescription_details "Chi tiết đơn thuốc (JSON/Text)"
        DATETIME created_at
        DATETIME updated_at
    }
```

---

## 5. Pharmacy Service ERD (`medicare_pharmacy_db`)

```mermaid
erDiagram
    MEDICINES {
        BIGINT id PK "AUTO_INCREMENT"
        VARCHAR name "Tên biệt dược / Thuốc"
        VARCHAR active_ingredient "Hoạt chất chính"
        VARCHAR unit "Đơn vị tính (Viên, Chai, Hộp...)"
        INT stock_quantity "Số lượng tồn kho"
        DECIMAL unit_price "Đơn giá xuất kho"
        DATETIME created_at
        DATETIME updated_at
    }
```
