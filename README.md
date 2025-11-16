# todo-vibes
Simple TODO API built with Spring Boot

## Description
A REST API for managing TODO items, built with Spring Boot. The todos are stored in-memory using a ConcurrentHashMap.

## Requirements
- Java 17 or higher
- Gradle (wrapper included)

## Building the Application
```bash
./gradlew build
```

## Running the Application
```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

## API Endpoints

### Get all todos
```bash
GET /api/todos
```

### Get a specific todo
```bash
GET /api/todos/{id}
```

### Create a new todo
```bash
POST /api/todos
Content-Type: application/json

{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": false
}
```

### Update a todo
```bash
PUT /api/todos/{id}
Content-Type: application/json

{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": true
}
```

### Delete a todo
```bash
DELETE /api/todos/{id}
```

## Running Tests
```bash
./gradlew test
```

## OpenAPI Specification

The API includes an OpenAPI 3.1 specification that describes all available endpoints.

### Accessing the OpenAPI Spec

- **JSON format**: `http://localhost:8080/v3/api-docs`
- **YAML format**: `http://localhost:8080/v3/api-docs.yaml`
- **Swagger UI** (interactive documentation): `http://localhost:8080/swagger-ui.html`

The Swagger UI provides an interactive interface to explore and test the API endpoints directly from your browser.

## Validation
The API includes input validation:
- `title` field is required and cannot be blank
- Validation errors return HTTP 400 Bad Request with error details
