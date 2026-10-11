# Payroll

Payroll is a Spring Boot application for managing employee records. It exposes a REST API for creating, reading, updating, and deleting employees, and uses Spring Data JPA with an H2 in-memory database for persistence.

## Features

- Employee CRUD operations
- REST API for employee management
- Input validation for required fields
- Java 17 and Spring Boot 3.2.0
- H2 database for local development
- Maven-based project setup

## Tech Stack

- Java 17
- Spring Boot 3.2.0
- Spring Web
- Spring Data JPA
- Spring Validation
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
```

## Application Overview

The application follows a standard Spring Boot layered architecture:

- Controller: handles HTTP requests
- Service: contains business logic
- Repository: stores and retrieves data using JPA
- Entity: represents the Employee model
- DTO: transfers employee data between layers

## Employee Model

The `Employee` entity contains:

- `id`
- `name`
- `role`

Validation rules:

- `name` is required
- `role` is required

## API Endpoints

Base URL: `http://localhost:8080`

### Get all employees

`GET /employees`

### Create a new employee

`POST /employees`

Request body:

```json
{
  "name": "John Doe",
  "role": "Developer"
}
```

### Get employee by ID

`GET /employees/{id}`

### Update employee

`PUT /employees/{id}`

### Delete employee

`DELETE /employees/{id}`

## Running the Application

Requirements:

- Java 17
- Maven

Run the application with:

```bash
./mvnw spring-boot:run
```

The app will start on:

```text
http://localhost:8080
```

## Configuration

The project uses `src/main/resources/application.properties` for Spring Boot configuration, including database settings and application behavior.

## License

This project is intended for educational and personal use.
