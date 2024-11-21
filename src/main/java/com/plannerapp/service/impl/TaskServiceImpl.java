package com.plannerapp.service.impl;

import com.plannerapp.model.dto.TaskDTO;
import com.plannerapp.model.dto.TaskHomeViewDTO;
import com.plannerapp.model.dto.TasksAddDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.UserEntity;
import com.plannerapp.repository.PriorityRepository;
import com.plannerapp.repository.TaskRepository;
import com.plannerapp.repository.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
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

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id, String username) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (username == null) {
                task.setAssignee(null);
            } else {
                UserEntity userEntity = userRepository.findByUsername(username);
                task.setAssignee(userEntity);
            }
            taskRepository.save(task);
        }
    }

    @Override
    public TaskHomeViewDTO getHomeViewDate(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        List<TaskDTO> assignedTasks = taskRepository.findByAssignee(userEntity)
                .stream()
                .map(TaskDTO::createFromTask)
                .toList();


        List<TaskDTO> availableTasks = taskRepository.getAllAvailableTasks()
                .stream()
                .map(TaskDTO::createFromTask)
                .toList();

        return new TaskHomeViewDTO(assignedTasks, availableTasks);
    }
}
