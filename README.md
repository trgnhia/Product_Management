# Product Management API

A RESTful API for managing **Products, Categories, and Users** built with **Spring Boot**, **Spring Security**, and **Spring Data JPA**.

This project was developed as part of backend learning to practice building a **clean, structured CRUD API** with authentication, validation, and pagination.

---

# Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- OpenAPI

---

# Features

## Product Management

- Create product
- Update product
- Delete product
- Get product by ID
- Search product by name
- Pagination and sorting

---

## Category Management

- CRUD operations
- One-to-many relationship with products

---

## User & Role Management

- User authentication
- Role-based authorization

Roles supported:

- USER
- ADMIN

---

## Validation & Exception Handling

- Request validation using `@Valid`
- Field validation using annotations like:
  - `@NotNull`
  - `@NotBlank`
  - `@Positive`
- Centralized exception handling using `GlobalExceptionHandler`

---

## Security

Spring Security is configured to protect all API endpoints.

Authentication methods used:

- Basic Authentication
- Role-based access control

Example:

- ADMIN → Full CRUD
- USER → Limited access

---

## API Documentation

Swagger UI is integrated for easy API testing.

