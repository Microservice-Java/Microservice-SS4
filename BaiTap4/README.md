# MediCare Hospital System - Discovery Server & Git Backend Config Server (SS4 - Exercise 4)

Dự án hoàn thiện hệ thống Microservices Quản lý Bệnh viện MediCare tích hợp **Eureka Discovery Server** và **Spring Cloud Config Server (Git Backend)**.

---

## 1. Kiến Trúc Tổng Thể

```text
               ┌────────────────────────────────────────────────────────┐
               │              Git Repository Backend                    │
               │  https://github.com/Microservice-Java/medicare-config-repo│
               └───────────────────────────┬────────────────────────────┘
                                           │
                                           ▼
               ┌────────────────────────────────────────────────────────┐
               │             Config Server (Port 8888)                  │
               └───────────────────────────┬────────────────────────────┘
                                           │ (Cung cấp cấu hình)
                                           ▼
┌───────────────────────────────────────────────────────────────────────────────┐
│                          Discovery Server (Eureka - 8761)                     │
└───────▲──────────────────────▲──────────────────────▲─────────────────▲───────┘
        │                      │                      │                 │
 ┌──────┴──────┐        ┌──────┴──────┐        ┌──────┴──────┐   ┌──────┴──────┐
 │patient-serv │        │doctor-serv  │        │appointm-serv│   │ ...-serv    │
 │ (Port 8081) │        │ (Port 8082) │        │ (Port 8083) │   │(8084, 8085) │
 └─────────────┘        └─────────────┘        └─────────────┘   └─────────────┘
```

---

## 2. Các Thành Phần Trong Hệ Thống

| Tên Service | Port | Loại Service | Link / Endpoint Kiểm Thử |
| :--- | :--- | :--- | :--- |
| `config-server` | `8888` | Config Server (Git Backend) | `http://localhost:8888/patient-service/default` |
| `discovery-server` | `8761` | Eureka Service Discovery | [http://localhost:8761](http://localhost:8761) |
| `patient-service` | `8081` | Microservice (Config & Eureka Client) | `http://localhost:8081/api/patients` |
| `doctor-service` | `8082` | Microservice (Config & Eureka Client) | `http://localhost:8082/api/doctors` |
| `appointment-service` | `8083` | Microservice (Config & Eureka Client) | `http://localhost:8083/api/appointments` |
| `medical-record-service` | `8084` | Microservice (Config & Eureka Client) | `http://localhost:8084/api/medical-records` |
| `pharmacy-service` | `8085` | Microservice (Config & Eureka Client) | `http://localhost:8085/api/medicines` |

---

## 3. Thứ Tự Khởi Chạy Bắt Buộc

> [!IMPORTANT]
> Vui lòng tuân thủ chính xác thứ tự 3 bước dưới đây khi khởi chạy hệ thống.

### Bước 1: Khởi Chạy Config Server (Port 8888)
```bash
cd config-server
./gradlew bootRun
```
*Kiểm tra:* Truy cập [http://localhost:8888/patient-service/default](http://localhost:8888/patient-service/default) để xác nhận Config Server đọc được file từ Git Backend.

### Bước 2: Khởi Chạy Discovery Server (Port 8761)
```bash
cd discovery-server
./gradlew bootRun
```
*Kiểm tra:* Truy cập Eureka Dashboard tại [http://localhost:8761](http://localhost:8761).

### Bước 3: Khởi Chạy Các Microservices (Ports 8081 - 8085)
Mở các cửa sổ terminal riêng và chạy:
```bash
cd patient-service && ./gradlew bootRun
cd doctor-service && ./gradlew bootRun
cd appointment-service && ./gradlew bootRun
cd medical-record-service && ./gradlew bootRun
cd pharmacy-service && ./gradlew bootRun
```

---

## 4. Kiểm Thử Giao Diện Eureka Dashboard

Sau khi tất cả các service khởi động thành công, mở trình duyệt truy cập:
`http://localhost:8761`

Bảng điều khiển **Eureka Dashboard** sẽ liệt kê cả 5 ứng dụng dưới mục **Instances currently registered with Eureka**:
- `PATIENT-SERVICE`
- `DOCTOR-SERVICE`
- `APPOINTMENT-SERVICE`
- `MEDICAL-RECORD-SERVICE`
- `PHARMACY-SERVICE`

Xem hình ảnh minh họa và chi tiết log đăng ký tại [screenshots/README.md](file:///d:/microservice/BaiTap/SS4/BaiTap4/screenshots/README.md).

---

## 5. Repository Chứa Config Files (medicare-config-repo)

- Git Repository URL: `https://github.com/Microservice-Java/medicare-config-repo`
- Local Repository Backup: `medicare-config-repo/`
