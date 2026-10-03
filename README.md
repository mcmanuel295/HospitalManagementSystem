# HospitalManagementSystem

Full-stack hospital management system with Spring Boot backend and vanilla HTML/CSS/JS frontend.

## Structure

```
.
├── backend/                # Spring Boot API
│   ├── src/
│   ├── pom.xml
│   └── ...
│
└── frontend/              # HTML/CSS/JS Dashboard
    ├── index.html         # Login page
    ├── dashboard.html      # Main dashboard
    ├── pages/
    │   ├── patients.html
    │   ├── doctors.html
    │   ├── appointments.html
    │   └── pharmacy.html
    ├── css/
    │   └── style.css
    ├── js/
    │   ├── app.js
    │   ├── auth.js
    │   └── api.js
    └── assets/
```

## Getting Started

### Frontend
```bash
cd frontend
python -m http.server 3000
# Open http://localhost:3000
```

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

## Features

- Patient management
- Doctor scheduling
- Appointment booking
- Pharmacy management
- Role-based access
- JWT authentication
