# Trello — Spring Boot Task Management System

A Trello-inspired task management application built with Java and Spring Boot. The project combines a server-side Thymeleaf UI with a REST API and PostgreSQL persistence.

## Features

- Folder management
- Task management with CRUD operations
- Task categories and folder/category assignment
- Task status workflow: `TODO`, `IN_TEST`, `DONE`, `FAILED`
- Server-side UI with Thymeleaf
- REST API under `/api/**`
- DTO + MapStruct mapping
- Request validation with Jakarta Validation
- Centralized REST error handling
- PostgreSQL persistence with Spring Data JPA
- Actuator health endpoint
- Environment-based database configuration

## Architecture

```text
src/main/java/practice/trello/
├── Controller/       # MVC and REST controllers
├── DTO/              # API/view data transfer objects
├── Entity/           # JPA entities
├── Exception/        # Application exceptions and global handlers
├── Mapper/           # MapStruct mappers
├── Repository/       # Spring Data repositories
└── Service/          # Business logic
```

## REST API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/folders` | List folders |
| GET | `/api/folders/{id}` | Get folder |
| GET | `/api/folders/{id}/tasks` | List folder tasks |
| POST | `/api/folders` | Create folder |
| DELETE | `/api/folders/{id}` | Delete folder |
| GET | `/api/tasks` | List tasks |
| GET | `/api/tasks/{id}` | Get task |
| POST | `/api/tasks` | Create task |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |
| GET | `/api/categories` | List categories |
| POST | `/api/categories` | Create category |
| DELETE | `/api/categories/{id}` | Delete category |
| POST | `/api/categories/{categoryId}/folders/{folderId}` | Assign category |
| DELETE | `/api/categories/{categoryId}/folders/{folderId}` | Remove category |

## Running locally

### Requirements

- JDK 25
- PostgreSQL 15+
- Gradle Wrapper

### Database

Create a PostgreSQL database named `Trello`, then configure:

```text
DB_URL=jdbc:postgresql://localhost:5432/Trello
DB_USERNAME=postgres
DB_PASSWORD=your-password
```

The application reads these values from environment variables. See `application-example.properties` for an example.

### Start

```bash
./gradlew bootRun
```

Windows:

```powershell
./gradlew.bat bootRun
```

The web UI is available at `/` and the REST API at `/api/**`.

Health check:

```text
/actuator/health
```

## Tech Stack

- Java 25
- Spring Boot 4
- Spring MVC
- Spring Data JPA / Hibernate
- PostgreSQL
- Thymeleaf
- MapStruct
- Lombok
- Jakarta Validation
- Spring Boot Actuator
- Gradle
