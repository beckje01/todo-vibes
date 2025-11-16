package com.beckje01.todovibes.controller;

import com.beckje01.todovibes.model.Todo;
import com.beckje01.todovibes.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "Todo management APIs")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @Operation(summary = "Get all todos", description = "Retrieve a list of all todo items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get todo by ID", description = "Retrieve a specific todo item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the todo"),
        @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<Todo> getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new todo", description = "Create a new todo item with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Todo created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input - title is required")
    })
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo created = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a todo", description = "Update an existing todo item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todo updated successfully"),
        @ApiResponse(responseCode = "404", description = "Todo not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input - title is required")
    })
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @Valid @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a todo", description = "Delete an existing todo item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Todo deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        if (todoService.deleteTodo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
