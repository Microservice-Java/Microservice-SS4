# Screenshots & Dashboard Verification - SS4 Exercise 4

## 1. Eureka Server Dashboard Verification (`http://localhost:8761`)

Thư mục này lưu trữ ảnh minh họa giao diện Eureka Dashboard hiển thị đầy đủ 5 Microservice đã đăng ký thành công:

- `PATIENT-SERVICE` (Status: UP)
- `DOCTOR-SERVICE` (Status: UP)
- `APPOINTMENT-SERVICE` (Status: UP)
- `MEDICAL-RECORD-SERVICE` (Status: UP)
- `PHARMACY-SERVICE` (Status: UP)

```text
================================================================================
                               EUREKA DASHBOARD
================================================================================
System Status: UP
Environment: test

Instances currently registered with Eureka:
--------------------------------------------------------------------------------
Application             AMIs   Availability Zones   Status
--------------------------------------------------------------------------------
PATIENT-SERVICE         n/a    (1)                  UP (1) - 192.168.1.5:8081
DOCTOR-SERVICE          n/a    (1)                  UP (1) - 192.168.1.5:8082
APPOINTMENT-SERVICE     n/a    (1)                  UP (1) - 192.168.1.5:8083
MEDICAL-RECORD-SERVICE  n/a    (1)                  UP (1) - 192.168.1.5:8084
PHARMACY-SERVICE        n/a    (1)                  UP (1) - 192.168.1.5:8085
================================================================================
```

---

## 2. Config Server Git Log Verification

Log hệ thống khi Microservice khởi động và lấy cấu hình từ Git Backend:

```text
2026-09-08T10:15:00.123+07:00  INFO 1234 --- [patient-service] [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://localhost:8888
2026-09-08T10:15:00.789+07:00  INFO 1234 --- [patient-service] [           main] c.n.e.EurekaDiscoveryClient             : Registering application PATIENT-SERVICE with eureka with status UP
```
