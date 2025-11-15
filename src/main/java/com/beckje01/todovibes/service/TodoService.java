package com.beckje01.todovibes.service;

import com.beckje01.todovibes.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TodoService {
    private final Map<String, Todo> todos = new ConcurrentHashMap<>();

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos.values());
    }

    public Optional<Todo> getTodoById(String id) {
        return Optional.ofNullable(todos.get(id));
    }

    public Todo createTodo(Todo todo) {
        String id = UUID.randomUUID().toString();
        todo.setId(id);
        todos.put(id, todo);
        return todo;
    }

    public Optional<Todo> updateTodo(String id, Todo updatedTodo) {
        if (!todos.containsKey(id)) {
            return Optional.empty();
        }
        updatedTodo.setId(id);
        todos.put(id, updatedTodo);
        return Optional.of(updatedTodo);
    }

    public boolean deleteTodo(String id) {
        return todos.remove(id) != null;
    }
}
