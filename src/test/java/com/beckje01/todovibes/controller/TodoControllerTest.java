package com.beckje01.todovibes.controller;

import com.beckje01.todovibes.model.Todo;
import com.beckje01.todovibes.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void getAllTodos_ReturnsListOfTodos() throws Exception {
        Todo todo1 = new Todo("1", "Test Todo 1", "Description 1", false);
        Todo todo2 = new Todo("2", "Test Todo 2", "Description 2", true);
        
        when(todoService.getAllTodos()).thenReturn(Arrays.asList(todo1, todo2));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Test Todo 1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].completed").value(true));
    }

    @Test
    void getTodoById_WhenExists_ReturnsTodo() throws Exception {
        Todo todo = new Todo("1", "Test Todo", "Description", false);
        
        when(todoService.getTodoById("1")).thenReturn(Optional.of(todo));

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Test Todo"));
    }

    @Test
    void getTodoById_WhenNotExists_ReturnsNotFound() throws Exception {
        when(todoService.getTodoById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/todos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTodo_ReturnsCreatedTodo() throws Exception {
        Todo inputTodo = new Todo(null, "New Todo", "New Description", false);
        Todo createdTodo = new Todo("1", "New Todo", "New Description", false);
        
        when(todoService.createTodo(any(Todo.class))).thenReturn(createdTodo);

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New Todo\",\"description\":\"New Description\",\"completed\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("New Todo"));
    }

    @Test
    void updateTodo_WhenExists_ReturnsUpdatedTodo() throws Exception {
        Todo updatedTodo = new Todo("1", "Updated Todo", "Updated Description", true);
        
        when(todoService.updateTodo(eq("1"), any(Todo.class))).thenReturn(Optional.of(updatedTodo));

        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Todo\",\"description\":\"Updated Description\",\"completed\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Todo"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void updateTodo_WhenNotExists_ReturnsNotFound() throws Exception {
        when(todoService.updateTodo(eq("999"), any(Todo.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/todos/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Todo\",\"description\":\"Updated Description\",\"completed\":true}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTodo_WhenExists_ReturnsNoContent() throws Exception {
        when(todoService.deleteTodo("1")).thenReturn(true);

        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTodo_WhenNotExists_ReturnsNotFound() throws Exception {
        when(todoService.deleteTodo("999")).thenReturn(false);

        mockMvc.perform(delete("/api/todos/999"))
                .andExpect(status().isNotFound());
    }
}
