package com.example.todo;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @GetMapping
    public Collection<Todo> getTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
        Todo todo = repository.findById(id);
        return todo != null ? ResponseEntity.ok(todo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return repository.save(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        if (repository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        todo.setId(id);
        return ResponseEntity.ok(repository.save(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (repository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
