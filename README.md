# todo-vibes
Simple TODO API built with Spring Boot

## Description
A REST API for managing TODO items, built with Spring Boot. The todos are stored in-memory using a ConcurrentHashMap.

## Requirements
- Java 17 or higher
- Maven 3.6 or higher

## Building the Application
```bash
mvn clean install
```

## Running the Application
```bash
mvn spring-boot:run
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
mvn test
```
