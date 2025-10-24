UMLStudio — Backend (Spring Boot)

Overview
--------
This repository contains the backend for UMLStudio — a small Spring Boot application that demonstrates a simple layered architecture (Controller → Service → Repository) for managing "projects".

Technology
----------
- Java 21
- Spring Boot (web, validation, data-jpa)
- H2 (runtime) and MySQL connector available
- Maven wrapper (mvnw)

Project structure (important files/folders)
------------------------------------------
- src/main/java/com/UMLStudio/backend/
  - BackendApplication.java
    - Purpose: Spring Boot application entry point. Boots the application.
    - Collaborators: Spring Boot runtime.

  - controller/
    - ProjectController.java
      - Purpose: REST controller exposing project-related HTTP endpoints under `/api/projects`.
      - Responsibilities: Accept and validate incoming HTTP requests (uses `@Valid`), map requests to service calls, return appropriate HTTP responses and status codes.
      - Collaborators: `ProjectService`, DTOs (`ProjectRequest`, `ProjectResponse`).

  - service/
    - ProjectService.java
      - Purpose: Application/business layer for project operations.
      - Responsibilities: Create projects, list all projects, fetch a single project. Map entity <-> DTO.
      - Collaborators: `ProjectRepository`, model `Project`, DTOs (`ProjectRequest`, `ProjectResponse`), `ResourceNotFoundException`.

  - repository/
    - ProjectRepository.java
      - Purpose: Data access layer. Extends `JpaRepository<Project, Long>` to provide CRUD operations for `Project` entities.
      - Responsibilities: Persistence operations via Spring Data JPA.
      - Collaborators: `Project` entity.

  - model/
    - Project.java
      - Purpose: JPA entity mapping for the `projects` table.
      - Responsibilities: Define fields (id, name, description, createdAt), lifecycle callback (`@PrePersist` to populate `createdAt`).
      - Collaborators: JPA/Hibernate and `ProjectRepository`.

  - dto/
    - ProjectRequest.java
      - Purpose: Input DTO used when creating a project.
      - Responsibilities: Carry request data and validate fields (e.g., `@NotBlank`, `@Size`).
      - Collaborators: `ProjectController`, `ProjectService`.

    - ProjectResponse.java
      - Purpose: Output DTO returned by the API.
      - Responsibilities: Represent the public shape of a project (id, name, description, createdAt).
      - Collaborators: `ProjectController`, `ProjectService`.

  - exception/
    - ResourceNotFoundException.java
      - Purpose: Custom runtime exception annotated with `@ResponseStatus(HttpStatus.NOT_FOUND)`.
      - Responsibilities: Thrown when a requested resource (project) is not found.
      - Collaborators: `ProjectService`, `GlobalExceptionHandler`.

    - GlobalExceptionHandler.java
      - Purpose: Centralized exception handling using `@ControllerAdvice`.
      - Responsibilities: Translate `MethodArgumentNotValidException` (validation failures) into HTTP 400 with a map of field -> message; translate `ResourceNotFoundException` into HTTP 404 with an error message.
      - Collaborators: Spring MVC, exceptions thrown by controllers/services.

- src/main/resources/
  - application.properties — configuration (database, ports, profiles).
  - static/ and templates/ — (currently present but may be unused) for static assets or server-side templates.

- test/
  - Integration and unit tests: `ProjectControllerIntegrationTest`, `BackendApplicationTests`.

- pom.xml — Maven build definition and dependencies.
- mvnw, mvnw.cmd and .mvn/ — Maven wrapper (included to run builds without requiring Maven installed globally).

API Endpoints (exposed by ProjectController)
--------------------------------------------
- POST /api/projects
  - Description: Create a new project.
  - Request body: JSON matching `ProjectRequest` (name (required), description (optional)).
  - Responses: 201 Created with `ProjectResponse` on success or 400 Bad Request with validation errors.

- GET /api/projects
  - Description: List all projects.
  - Responses: 200 OK with array of `ProjectResponse`.

- GET /api/projects/{id}
  - Description: Get a single project by id.
  - Responses: 200 OK with `ProjectResponse` or 404 Not Found when not present.

Build & run
-----------
From the project root (this repository contains the Maven wrapper):

Windows (cmd.exe):

mvnw clean package
mvnw spring-boot:run

Or, with global Maven installed:

mvn clean package
mvn spring-boot:run

Notes & assumptions
-------------------
- The application uses Spring Data JPA and expects a datasource. By default the pom includes H2 for runtime; configure `application.properties` if you want a persistent MySQL datasource.
- DTOs are used to decouple entity from API representation. Validation annotations are on `ProjectRequest`.
- Global exception handler centralizes validation and not-found responses.

Where to extend
----------------
- Add update/delete endpoints in `ProjectController` and corresponding service methods.
- Add DTO mappers or use MapStruct for more complex mapping logic.
- Add service-level validation or business rules in `ProjectService`.

Contact / Troubleshooting
-------------------------
If you see compilation or mapping errors when running, ensure your Java SDK version is compatible (pom specifies Java 21) and run the Maven wrapper (`mvnw`) from the project root.


(last updated: automated README generated)

