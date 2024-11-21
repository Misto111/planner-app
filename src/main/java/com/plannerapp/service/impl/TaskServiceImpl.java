package com.plannerapp.service.impl;

import com.plannerapp.model.dto.TasksAddDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.repository.PriorityRepository;
import com.plannerapp.repository.TaskRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void add(TasksAddDTO tasksAddDTO) {
        Priority priority = priorityRepository.findByName(tasksAddDTO.getPriority());

        if (priority != null) {
           Task task = new Task()
                   .setDescription(tasksAddDTO.getDescription())
                   .setDueDate(LocalDate.parse(tasksAddDTO.getDueDate()))
                   .setPriority(priority);

           taskRepository.save(task);
        }
    }
}
