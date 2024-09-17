package com.example.task.service;

import com.example.task.dto.TaskDto;
import com.example.task.entity.Task;

public interface TaskService {

    Task create(TaskDto taskDto);
    Task update(TaskDto taskDto);
    Task get(String id);
    void delete(String id);

}
