# Postman Test Screenshots - MediCare Microservices (SS4 - Exercise 2)

Thư mục chứa toàn bộ 10 hình ảnh kiểm thử Postman REST API cho 5 Microservice trong hệ thống MediCare (mỗi service bao gồm 2 request: `POST` khởi tạo và `GET` danh sách).

---

## Danh Sách Screenshots Chi Tiết Theo Service

### 1. Patient Service (Port 8081)
- **POST Create Patient**: `patient_service_create.png` (`POST http://localhost:8081/api/patients` - Status 201 Created)
- **GET All Patients**: `patient_service_get_all.png` (`GET http://localhost:8081/api/patients` - Status 200 OK)

### 2. Doctor Service (Port 8082)
- **POST Create Doctor**: `doctor_service_create.png` (`POST http://localhost:8082/api/doctors` - Status 201 Created)
- **GET All Doctors**: `doctor_service_get_all.png` (`GET http://localhost:8082/api/doctors` - Status 200 OK)

### 3. Appointment Service (Port 8083)
- **POST Create Appointment**: `appointment_service_create.png` (`POST http://localhost:8083/api/appointments` - Status 201 Created)
- **GET All Appointments**: `appointment_service_get_all.png` (`GET http://localhost:8083/api/appointments` - Status 200 OK)

### 4. Medical Record Service (Port 8084)
- **POST Create Medical Record**: `medical_record_service_create.png` (`POST http://localhost:8084/api/medical-records` - Status 201 Created)
- **GET All Medical Records**: `medical_record_service_get_all.png` (`GET http://localhost:8084/api/medical-records` - Status 200 OK)

### 5. Pharmacy Service (Port 8085)
- **POST Create Medicine**: `pharmacy_service_create.png` (`POST http://localhost:8085/api/medicines` - Status 201 Created)
- **GET All Medicines**: `pharmacy_service_get_all.png` (`GET http://localhost:8085/api/medicines` - Status 200 OK)
