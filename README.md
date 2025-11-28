
# User Profile Backend Service

Submitted by Jyotibroto Roy Laskar

A robust, scalable backend service designed to manage user details for Aadhaar applications. Built with Java Spring Boot, complying with strict requirements for SQL pagination, validation, and data integrity.

#  Table of Contents
    Tech Stack

    Quick Start

    Architectural Decisions

    API Endpoints

    Deliverables Checklist

#  Tech Stack
    Component               Technology                Description

    Language                Java 17                   Core Logic & Syntax

    Framework               Spring Boot 3.4.x         REST API & Dependency Injection

    Database                H2 (PostgreSQL Mode)      In-Memory Persistence (Production ready for Postgres)

    Build Tool              Maven                     Dependency Management

    Persistence            JPA + EntityManager        ORM & Native SQL Execution

# Quick Start (How to Run)

Prerequisites

    ✅ Java 17 or higher (java -version)

    ✅ Maven (or use the included mvnw wrapper)

Steps

1. Clone the repository

        git clone [https://github.com/JyotibrotoRoy/aadhaar-backend-service.git](https://github.com/JyotibrotoRoy/AadhaarService)

        cd aadhaar-backend-service



2. Build and Run

    # Linux/Mac
        ./mvnw spring-boot:run

    # Windows
        mvnw spring-boot:run

3. Verify Application

        Server: http://localhost:8080

        H2 Console: http://localhost:8080/h2-console

            JDBC URL: jdbc:h2:mem:aadhaar_db

            User: sa

            Password: password

#  Architectural Decisions

1. Database Strategy: H2 (PostgreSQL Mode)

        Why? To ensure the application is "Plug-and-Play" for reviewers. It requires zero local setup while supporting standard PostgreSQL syntax.

2. Strict SQL Compliance (LIMIT / OFFSET)
The problem statement mandated the use of Native SQL. Instead of hiding behind JPA, the UserService executes:

        SELECT * FROM users ORDER BY :sortBy LIMIT :limit OFFSET :offset

Security: sortBy parameter is validated against an Allowlist (id, name, email) to prevent SQL Injection.

3. Validation & Data Integrity

        nput: Handled via Jakarta Validation (@NotBlank, @Email, @Pattern).

        Database: Constraints enforced at schema level (UNIQUE email/aadhaar).

        Performance: Explicit B-Tree Index on name column for fast sorting.

# API Endpoints

        Method             Endpoint                 Description

        POST               /api/users               Create a new user

        GET                /api/users/{id}          Get user by ID
        
        GET                /api/users               List users (Paginated)

1. Create User

    Request Body:

            {
                "name": "John Doe",
                "email": "john@example.com",
                "aadhaarId": "123456789012",
                "phoneNumber": "9876543210",
                "address": "123 Main St, Bangalore"
            }


2. List Users (Paginated)

    Parameters:

        page: Page number (default: 0)

        size: Items per page (default: 10)

        sortBy: Sort column (id, name, email)

        Example:
        GET /api/users?page=0&size=5&sortBy=name

#  Deliverables Checklist

[✅] Database Migration: Included as src/main/resources/schema.sql

[✅] Project Structure: Documented in FOLDER_STRUCTURE.md

[✅] Run Instructions: Provided in this README

[✅] SQL Requirements: Implemented via Native Query in Service layer