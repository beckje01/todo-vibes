package com.example.todo;

import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TodoRepository {

    private final Map<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Collection<Todo> findAll() {
        return todos.values();
    }

    public Todo findById(Long id) {
        return todos.get(id);
    }

    public Todo save(Todo todo) {
        if (todo.getId() == 0) {
            todo.setId(counter.incrementAndGet());
        }
        todos.put(todo.getId(), todo);
        return todo;
    }

    public void deleteById(Long id) {
        todos.remove(id);
    }
}
