# 🏋️‍♂️ FitLife Gym Management API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Security](https://img.shields.io/badge/Spring_Security-JWT-red?style=for-the-badge)
![Flyway](https://img.shields.io/badge/Flyway-Migration-ff69b4?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger)

## 📖 About The Project
**FitLife API** is a robust, scalable, and secure RESTful backend system designed for gym and fitness center management. Built with **Spring Boot 3** and **Java 17**, it provides a complete ecosystem for managing memberships, fitness packages, subscriptions, and daily check-ins.

The project strictly follows **Clean Code** principles, **Defensive Programming**, and modern backend architectural patterns.

## ✨ Core Features
* **🔒 Advanced Security:** Stateless authentication using **JWT (JSON Web Tokens)**. Passwords are securely hashed using **BCrypt**.
* **👥 Role-Based Access Control (RBAC):** API endpoint protection based on user roles (`ADMIN`, `STAFF`).
* **🗄️ Database Versioning:** Automated database schema tracking and migration using **Flyway**.
* **📝 Interactive Documentation:** Auto-generated API documentation and testing UI using **Swagger (OpenAPI 3)**.
* **🌐 CORS Enabled:** Ready to be integrated with modern frontend frameworks (React.js, Vue.js, Angular).

## 🏗️ Database Schema (Core Modules)
The system operates on a normalized MySQL relational database consisting of 5 core entities:
1. `users`: System accounts (Admin, Staff) with credentials and roles.
2. `members`: Gym customer profiles and contact information.
3. `packages`: Available fitness plans (e.g., 1-month yoga, 1-year VIP).
4. `subscriptions`: Transaction records mapping members to purchased packages with valid date ranges.
5. `check_in_history`: Daily turnstile logs validating active subscriptions upon entry.

## 🚀 Getting Started

### Prerequisites
* **JDK 17** or higher
* **Maven** 3.8+
* **MySQL** 8.0+

### Installation & Setup
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/](https://github.com/)lqhuy03/fitlife-gym-management.git
   cd fitlife-gym-management
   

2. **Prepare the repository:**

   Open your MySQL console or GUI tool (DataGrip/Workbench) and create an empty database:
   ```sql
   CREATE DATABASE fitlife_db;
   ```
   *(Note: You don't need to run any schema scripts manually. Flyway will automatically create all tables upon startup).*


3. **Configure Application Properties:**

    Navigate to `src/main/resources/application.yml` and update your MySQL credentials:
    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/fitlife_db?useSSL=false
        username: root
        password: your_secure_password
    ```


4. **Run the Application:**
   
    Execute the Spring Boot application using your IDE, or via Maven:

    ```bash
    mvn spring-boot:run
    ```
   
## 📚 API Documentation (Swagger UI)
Once the server is running (default port 8080), you can access the interactive API documentation to explore and test all endpoints.

👉 **Swagger UI URL:** http://localhost:8080/swagger-ui/index.html

**How to test secure APIs:**
1. Register or manually insert a User into the database with `role = STAFF` or `ADMIN`.
2. Send a `POST` request to `/auth/login` with your credentials. 
3. Copy the returned `token`.
4. Click the `Authorize` button (padlock icon) at the top of the Swagger UI and paste your token. All subsequent requests will now be authenticated!

---
Developed by Le Quang Huy - 2026