# Nurture Hospital Management System

Nurture is a Spring Boot hospital management system for coordinating patients, doctors, pharmacists, receptionists, administrators, and owners through one role-aware API. It also includes a responsive public home page for entering the care workspace.

## Features

- Patient registration, profiles, assigned doctors, and patient-doctor pairing
- Doctor management, specialization lookup, availability, and assigned patients
- Pharmacist and medicine management, including distributors and inventory actions
- Administrator management of users and hospital roles
- JWT-based authentication through `/api/v1/users/login`
- Public responsive frontend at `/` and `/home`
- MySQL persistence through Spring Data JPA and Hibernate

## Technology

- Java 17
- Spring Boot 3.5.5
- Spring Web and Spring Security
- Spring Data JPA and Hibernate
- MySQL
- JJWT for JSON Web Tokens
- Maven Wrapper
- Plain HTML, CSS, and JavaScript frontend

## Requirements

- JDK 17 or newer
- MySQL 8 or a compatible MySQL server
- A database named `hospitalManagementDb`
- Maven is optional because the project includes `mvnw` and `mvnw.cmd`

## Configuration

Update `src/main/resources/application.properties` with local values. Do not commit database credentials, JWT secrets, or provider API keys.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospitalManagementDb
spring.datasource.username=your-mysql-user
spring.datasource.password=your-mysql-password
jwt.secret.key=your-base64-jwt-secret
OPENAI_API_KEY=your-provider-key
```

The AI integration uses the OpenAI-compatible provider settings configured in the project and reads its key from the `OPENAI_API_KEY` environment variable.

## Run Locally

Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

macOS or Linux:

```bash
./mvnw spring-boot:run
```

Open [http://localhost:8080/](http://localhost:8080/) to view the Nurture home page. The sign-in dialog submits credentials to the login API and stores the returned JWT in browser local storage.

## API Areas

All API routes use the `/api/v1` prefix.

| Area | Base route | Purpose |
| --- | --- | --- |
| Authentication | `/users` | Login and JWT issuance |
| Patients | `/patients` | Registration, profiles, assignments, and doctor pairing |
| Doctors | `/doctors` | Doctor records, availability, specialties, and assigned patients |
| Pharmacists | `/pharmacists` | Pharmacist records and administration |
| Medicines | `/medicines` | Inventory, distributors, and medicine lifecycle |
| Administrators | `/admins` | User and administrator management |
| Owners | `/owners` | Owner-related administration |

Most operational routes require a valid bearer token:

```http
Authorization: Bearer <jwt-token>
```

The home page, its CSS and JavaScript assets, and the login endpoint are publicly accessible according to the current security configuration.

## Roles

The system supports:

- `ADMIN`
- `PATIENT`
- `PHARMACIST`
- `DOCTOR`
- `RECEPTIONIST`
- `OWNER`

Protected controller methods use Spring Security role checks to control access.

## Frontend Structure

```text
src/main/resources/static/
├── home.html
├── css/home.css
└── js/home.js
```

`HomeController` forwards `/` and `/home` to `home.html`. `SecurityConfiguration` permits the page and its static assets through the JWT filter chain.

## Verify the Project

Run tests:

```powershell
.\mvnw.cmd test
```

Compile without tests:

```powershell
.\mvnw.cmd -DskipTests compile
```

## Project Layout

```text
src/main/java/com/mcmanuel/HospitalManagementSystem/
├── configuration/   Security and JWT filter configuration
├── controller/      REST and home-page controllers
├── entity/          JPA domain models
├── pojo/            Shared request/value types and roles
├── repository/      Spring Data repositories
└── service/         Business logic and service interfaces
```

## Security Notes

- Keep credentials and secrets outside source control.
- Use a strong, unique, Base64-encoded JWT secret outside development.
- Define a production migration strategy instead of relying on automatic schema updates.
- Review public API matchers before deploying outside a local environment.
