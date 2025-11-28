aadhaar-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/boseprofessional/aadhaarService/
│   │   │       ├── controller/       # For REST API Endpoints & Request Handling
│   │   │       │   └── UserController.java
│   │   │       │
│   │   │       ├── service/          # For Business Logic & Native SQL Execution
│   │   │       │   └── UserService.java
│   │   │       │
│   │   │       ├── repository/       # Data Access Layer (JPA Interfaces)
│   │   │       │   └── UserRepository.java
│   │   │       │
│   │   │       ├── entity/           # Database Schema Mapping (ORM)
│   │   │       │   └── User.java
│   │   │       │
│   │   │       ├── dto/              # Data Transfer Objects (Records)
│   │   │       │   └── UserDTOs.java
│   │   │       │
│   │   │       ├── exception/        # Global Error Handling
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       └── AadhaarServiceApplication.java  # Main Entry Point
│   │   │
│   │   └── resources/
│   │       ├── application.properties # Configuration (DB URL, Logging)
│   │       └── schema.sql             # SQL Migration Script (DDL)
│   │
│   └── test/                          # Unit & Integration Tests
│       └── java/...
│
├── pom.xml                            # Maven Dependencies
├── README.md                          # Run Instructions & Documentation
└── FOLDER_STRUCTURE.md                # This file