package com.plannerapp.model.dto;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.service.annotation.DateInFuture;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TasksAddDTO {

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @DateInFuture(message = "Due Date must be in the future!")
    private String dueDate;

    @NotNull(message = "You must select a priority")
    private PriorityName priority;

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public TasksAddDTO setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TasksAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public TasksAddDTO setPriority(PriorityName priority) {
        this.priority = priority;
        return this;
    }
}
