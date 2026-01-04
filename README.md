
## Project Progress – Student Management System

## Tech Stack

* **Backend:** Spring Boot
* **Database:** PostgreSQL
* **API Testing:** Postman
* **Architecture:** Layered
  (**Controller → Service → Repository → Entity**)
  **evolved into DTO-based layered flow**

### Updated Architecture Flow

#### Request Flow

```
Client
  |
  v
Controller
  |  (RequestDTO)
  v
Service
  |  (Entity)
  v
Repository
  |
  v
Database
```
#### Response Flow

```
Database
  ^
  |
Repository
  ^
  |  (Entity)
Service
  ^
  |  (ResponseDTO)
Controller
  ^
  |
Client

```

---

### **31st December 2025 (00:00 hours – Midnight)**

I started working on the **Student Management System** project using **Spring Boot**, **PostgreSQL**, and **Postman**.

* Designed and implemented the **layered architecture**, including:

  * **Entity Layer**
  * **Repository Layer**
  * **Service Layer**
  * **Controller Layer**
* Understood the **flow of data across layers**:

  * Controller → Service → Repository → Database
* Developed an API to **create a Student entity** and persist it into the database.

---

### **1st January 2026**

Focused on understanding **Spring Boot response handling**, exception management, and improving API structure.

* Learned about the **ResponseEntity** class in Spring:

  * Used to control **HTTP status codes**, **response body**, and **response headers**
* Learned about **ResponseStructure**:

  * Used to provide a **standardized and consistent API response**
  * Contains:

    * **HTTP status codes** (`200 OK`, `201 CREATED`, `404 NOT FOUND`, `500 INTERNAL SERVER ERROR`)
    * **Message** (success or error description)
    * **Data** (actual response payload)
* Understood how **ResponseStructure works together with ResponseEntity**:

  * `ResponseStructure` holds the response details
  * `ResponseEntity` wraps the `ResponseStructure` and sends it to the client
* Implemented **Global Exception Handling**:

  * Used `@ControllerAdvice` and `@ExceptionHandler`
  * Centralized exception handling across the application
  * Returned meaningful error responses using **ResponseStructure + ResponseEntity**
* Implemented **CRUD APIs**:

  * Create
  * Read
  * Update
  * Delete
* Gained a clear understanding of the **complete request–response lifecycle**:

  * Client → Controller → Service → Repository → Database
  * Database → Repository → Service → Controller → Client

---

### **2nd January 2026**

Worked on data handling and API enhancement.

* Inserted **100 Student records** into the PostgreSQL database.
* Developed APIs to **sort student data** based on:

  * Student ID
  * Student last name
* Improved understanding of:

  * Query handling
  * Sorting logic
  * Clean and scalable API design

---

### **3rd January 2026**

**Pagination Implementation**

* Implemented pagination using Spring Data JPA
* Used `Pageable` and `Page<T>` for fetching paginated student records
* Allowed client to control:

  * Page number
  * Page size
  * Sorting (optional) — by default `Sort.unsorted()`

**Concepts Reinforced**

* Difference between:

  * `Page`
  * `Slice`
  * `List`
* Why pagination should be handled at repository level

---

### **4th January 2026**

#### **Concepts Covered**

* Introduced **DTO (Data Transfer Object)** pattern
* Implemented **StudentRequestDTO** and **StudentResponseDTO**
* Understood why entities should not be exposed directly to controllers
* Learned the importance of separating **API contract** from **database entities**

#### **Mapper Implementation**

* Created a dedicated **StudentMapper** class
* Converted:

  * `StudentRequestDTO → Student Entity`
  * `Student Entity → StudentResponseDTO`
* Ensured mapping logic is centralized and reusable

#### **Service Layer Responsibility**

* Handled **Course–Student relationship** properly
* Fetched `Course` entity using `courseId` in service layer
* Passed managed `Course` entity to mapper (avoiding direct ID-to-entity mapping)

#### **Validation Improvements**

* Added validation annotations on **DTO layer** (`@NotNull`, `@NotBlank`, `@Email`)
* Enabled request validation using `@Valid` in controller
* Ensured invalid requests fail early (before hitting repository layer)

---

## Key Learnings So Far

* Strong understanding of **Spring Boot layered architecture**
* Clean separation of concerns between application layers
* Standardized API responses using **ResponseEntity + ResponseStructure**
* Centralized and consistent error handling using **Global Exception Handling**
* Improved handling of HTTP status codes and failure scenarios
* Hands-on experience with **PostgreSQL**, **REST APIs**, and **Postman**

---
