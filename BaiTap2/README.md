# MediCare Hospital System - Microservices REST API (SS4 - Exercise 2)

Hệ thống Microservices Quản lý Bệnh viện MediCare bao gồm 5 dịch vụ độc lập được thiết kế theo nguyên tắc **Database-per-Service** và cấu trúc 3 tầng chuẩn (**Controller → Service → Repository**).

---

## 1. Danh Sách Các Microservice

| Service | Port | Database MySQL | Base Endpoint | Mô Tả |
| :--- | :--- | :--- | :--- | :--- |
| `patient-service` | `8081` | `medicare_patient_db` | `/api/patients` | Quản lý thông tin hồ sơ bệnh nhân |
| `doctor-service` | `8082` | `medicare_doctor_db` | `/api/doctors` | Quản lý thông tin bác sĩ và chuyên khoa |
| `appointment-service` | `8083` | `medicare_appointment_db` | `/api/appointments` | Quản lý lịch hẹn khám bệnh |
| `medical-record-service` | `8084` | `medicare_medical_record_db` | `/api/medical-records` | Quản lý hồ sơ bệnh án và chẩn đoán |
| `pharmacy-service` | `8085` | `medicare_pharmacy_db` | `/api/medicines` | Quản lý kho thuốc và nhà cung cấp |

---

## 2. Kiến Trúc Cấu Trúc Mã Nguồn (Layered Architecture)

Mỗi Microservice được xây dựng theo kiến trúc phân tầng chuẩn của Spring Boot:

```text
[Client / Postman]
       │
       ▼
┌──────────────┐
│  Controller  │  (REST API Endpoints, Request Body Validation, HTTP Status Codes)
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Service    │  (Business Logic, Entity <-> DTO Mapping, Transaction Management)
└──────┬───────┘
       │
       ▼
┌──────────────┐
│  Repository  │  (Spring Data JPA Data Access Layer)
└──────┬───────┘
       │
       ▼
┌──────────────┐
│ MySQL / H2   │  (Isolated Database per Service)
└──────────────┘
```

---

## 3. Nguyên Tắc Thiết Kế Database-per-Service

- Mỗi service quản lý cơ sở dữ liệu hoàn toàn riêng biệt.
- **Không sử dụng `@ManyToOne` hay `@JoinColumn`** xuyên database giữa các service.
- Các liên kết logic (ví dụ: `patientId` hoặc `doctorId` trong `Appointment` và `MedicalRecord`) được lưu giữ dưới dạng số nguyên `Long` thuần túy để đảm bảo loose coupling (ghép nối lỏng).

---

## 4. Hướng Dẫn Khởi Chạy Hệ Thống

### Yêu Cầu Tiền Đề:
- Java OpenJDK 21
- MySQL Server 8.x (Username: `root`, Password: `root`) hoặc H2 Database in-memory.

### Các Bước Khởi Chạy Từng Service:

1. Chuyển vào thư mục service mong muốn:
   ```bash
   cd patient-service
   # Hoặc: cd doctor-service, cd appointment-service, cd medical-record-service, cd pharmacy-service
   ```

2. Chạy ứng dụng bằng Gradle:
   ```bash
   ./gradlew bootRun
   ```

---

## 5. Kiểm Thử API Bằng Postman Collection

Bộ test API hoàn chỉnh chứa 25 request (FULL CRUD cho cả 5 service) được đặt tại:
`postman/MediCare_API_Collection.json`

### Các Bước Import VÀ Test:
1. Mở ứng dụng **Postman**.
2. Chọn **Import** -> Chọn file `postman/MediCare_API_Collection.json`.
3. Kiểm thử các REST API theo thứ tự:
   - `POST` để khởi tạo dữ liệu mẫu.
   - `GET` / `GET {id}` để đọc thông tin.
   - `PUT {id}` để cập nhật dữ liệu.
   - `DELETE {id}` để xóa tài nguyên.
