# MediCare Hospital System - Centralized Configuration with Spring Cloud Config Server (SS4 - Exercise 3)

Dự án triển khai **Spring Cloud Config Server** để quản lý cấu hình tập trung cho 5 Microservice trong hệ thống Bệnh viện MediCare.

---

## 1. Kiến Trúc Quản Lý Cấu Hình Tập Trung (Centralized Configuration)

```text
┌──────────────────────────────────────────────────────────┐
│                   Config Server (8888)                   │
│   (Profile: native, Search path: classpath:/config-repo) │
└────────────────────────────┬─────────────────────────────┘
                             │
       ┌─────────────────────┼─────────────────────┐
       ▼                     ▼                     ▼
┌──────────────┐      ┌──────────────┐      ┌──────────────┐
│patient-service│     │doctor-service│      │ ...-service  │
│ (Port: 8081) │      │ (Port: 8082) │      │(Ports: 8083+)│
└──────────────┘      └──────────────┘      └──────────────┘
```

Mỗi Microservice khi khởi động chỉ cần khai báo:
```yaml
spring:
  application:
    name: patient-service  # hoặc doctor-service, appointment-service, ...
  config:
    import: "configserver:http://localhost:8888"
```
Toàn bộ thông tin cấu hình Server Port, MySQL Datasource URL, Username, Password, và JPA settings được tải tự động từ Config Server.

---

## 2. Danh Sách File Cấu Hình Tại Config Server (`config-repo/`)

Các file cấu hình tập trung nằm tại thư mục `config-server/src/main/resources/config-repo/`:

| File Cấu Hình | Application Target | Server Port Configured | Database Target |
| :--- | :--- | :--- | :--- |
| `patient-service.yml` | `patient-service` | `8081` | `medicare_patient_db` |
| `doctor-service.yml` | `doctor-service` | `8082` | `medicare_doctor_db` |
| `appointment-service.yml` | `appointment-service` | `8083` | `medicare_appointment_db` |
| `medical-record-service.yml` | `medical-record-service` | `8084` | `medicare_medical_record_db` |
| `pharmacy-service.yml` | `pharmacy-service` | `8085` | `medicare_pharmacy_db` |

---

## 3. Thứ Tự Khởi Chạy Bắt Buộc

> [!IMPORTANT]
> Phải khởi chạy **Config Server (port 8888)** TRƯỚC khi khởi chạy bất kỳ Microservice nào.

### Bước 1: Khởi Chạy Config Server (Port 8888)
```bash
cd config-server
./gradlew bootRun
```
*Xác nhận Config Server đã sẵn sàng bằng cách truy cập:* [http://localhost:8888/patient-service/default](http://localhost:8888/patient-service/default)

### Bước 2: Khởi Chạy Các Microservice (Port 8081 - 8085)
```bash
# Mở các cửa sổ terminal riêng cho từng service:
cd patient-service && ./gradlew bootRun
cd doctor-service && ./gradlew bootRun
cd appointment-service && ./gradlew bootRun
cd medical-record-service && ./gradlew bootRun
cd pharmacy-service && ./gradlew bootRun
```

---

## 4. Minh Họa Log Khởi Động Khi Microservice Lấy Config

Khi khởi động `patient-service`, log hiển thị rõ ràng thông tin kết nối tới Config Server:

```text
2026-09-08T10:00:00.123+07:00  INFO 1234 --- [patient-service] [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://localhost:8888
2026-09-08T10:00:00.456+07:00  INFO 1234 --- [patient-service] [           main] c.c.c.ConfigServicePropertySourceLocator : Located environment: name=patient-service, profiles=[default], label=null, version=null, state=null
2026-09-08T10:00:01.789+07:00  INFO 1234 --- [patient-service] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8081 (http)
```

---

## 5. Truy Cấp Endpoint Kiểm Thử Cấu Hình

| Service Config URL | Trạng Thái Trả Về |
| :--- | :--- |
| `http://localhost:8888/patient-service/default` | Trả về thông tin JSON cấu hình cho `patient-service` |
| `http://localhost:8888/doctor-service/default` | Trả về thông tin JSON cấu hình cho `doctor-service` |
| `http://localhost:8888/appointment-service/default` | Trả về thông tin JSON cấu hình cho `appointment-service` |
| `http://localhost:8888/medical-record-service/default` | Trả về thông tin JSON cấu hình cho `medical-record-service` |
| `http://localhost:8888/pharmacy-service/default` | Trả về thông tin JSON cấu hình cho `pharmacy-service` |

---

## 6. Postman Collection

File Postman Collection được lưu trữ tại `postman/MediCare_API_Collection.json` hỗ trợ kiểm thử cả Config Server endpoints và các CRUD REST APIs trên từng microservice.
