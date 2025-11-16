package com.beckje01.todovibes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Todo item")
public class Todo {
    @Schema(description = "Unique identifier of the todo", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    
    @NotBlank(message = "Title is required")
    @Schema(description = "Title of the todo", example = "Buy groceries", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    
    @Schema(description = "Detailed description of the todo", example = "Milk, eggs, bread")
    private String description;
    
    @Schema(description = "Completion status of the todo", example = "false")
    private boolean completed;

    public Todo() {
    }

    public Todo(String id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
