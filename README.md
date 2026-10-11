# Payroll

<div align="center">

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)
![License](https://img.shields.io/badge/License-Open%20Source-lightgrey)

</div>

A simple Spring Boot payroll application for managing employee records. The project exposes a REST API for creating, retrieving, updating, and deleting employees using Spring Data JPA and an embedded H2 database.

## Overview

This application demonstrates a clean backend architecture for a payroll system with the following responsibilities:

- Employee management via REST endpoints
- Domain modeling with JPA entities
- Data persistence with H2 in-memory database
- Validation for required fields
- Maven-based project setup for easy local execution

## Features

- Create new employees
- View all employees
- Fetch employee details by ID
- Update employee information
- Delete employee records
- Validation for `name` and `role`
- Spring Boot-based REST API

## Tech Stack

- Java 25
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- Jakarta Validation
- Lombok
- H2 Database
- Maven

## Project Structure

```text
Payroll/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/dev/payroll/
│   │   │   ├── controller/
│   │   │   ├── dao/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── util/
│   │   │   └── PayrollApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── .gitattributes
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── .github/
```

## Architecture

The project follows a standard layered Spring Boot structure:

- `controller` – exposes REST endpoints
- `service` – contains business logic
- `repository` – database access via JPA
- `entity` – persistence model
- `dto` – request/response payload objects
- `util` – helper classes, if any

## Employee Model

The `Employee` entity contains:

- `id`
- `name`
- `role`

Validation rules:

- `name` must not be blank
- `role` must not be blank

## API Endpoints

Base URL:

```text
http://localhost:8080
```

### 1) Get all employees

```http
GET /employees
```

### 2) Create a new employee

```http
POST /employees
```

Example request body:

```json
{
  "name": "John Doe",
  "role": "Developer"
}
```

### 3) Get employee by ID

```http
GET /employees/{id}
```

### 4) Update employee

```http
PUT /employees/{id}
```

Example request body:

```json
{
  "name": "John Doe",
  "role": "Senior Developer"
}
```

### 5) Delete employee

```http
DELETE /employees/{id}
```

## Prerequisites

Before running the project, make sure you have:

- Java 17+
- Maven
- A terminal or IDE such as IntelliJ IDEA or VS Code

## Running the Application

Clone the project and run:

```bash
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

### Docker runtime

Build and run the Java 25 container image:

```bash
docker build -t payroll:latest .
docker run --rm -p 8080:8080 payroll:latest
```

Or with Docker Compose:

```bash
docker compose up --build
```

## Configuration

The application configuration is defined in:

```text
src/main/resources/application.properties
```

This project uses H2 as the default in-memory database for local development.

## Database

The app is configured for local persistence with H2 and can be accessed through the configured H2 console if enabled in the application properties.

## License

This project is intended for learning, local development, and personal use.

## Future Enhancements

- Add employee salary and payroll calculations
- Add authentication and authorization
- Introduce pagination and sorting
- Add Swagger/OpenAPI documentation
- Add unit and integration tests for all endpoints
