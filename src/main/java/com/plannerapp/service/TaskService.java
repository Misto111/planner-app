package com.plannerapp.service;

import com.plannerapp.model.dto.TaskHomeViewDTO;
import com.plannerapp.model.dto.TasksAddDTO;

public interface TaskService {

    void add(TasksAddDTO tasksAddDTO);

    void remove(Long id);

    void assign(Long id, String username);

    TaskHomeViewDTO getHomeViewDate(String username);
}
