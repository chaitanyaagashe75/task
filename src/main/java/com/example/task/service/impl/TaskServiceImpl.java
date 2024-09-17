package com.example.task.service.impl;

import com.example.task.dto.TaskDto;
import com.example.task.entity.Task;
import com.example.task.enums.TaskStatus;
import com.example.task.exception.TaskException;
import com.example.task.repository.TaskRepository;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Override
    public Task create(TaskDto taskDto) {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setEndDate(taskDto.getEndDate());
//        task.setStatus("PENDING");
        task.setStatus(TaskStatus.PENDING.getLabel());
        return taskRepository.save(task);
    }

    @Override
    public Task update(TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(taskDto.getId());
        if(optionalTask.isPresent()) {
            Task task = new Task();
            task.setId(taskDto.getId());
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setEndDate(taskDto.getEndDate());
            if(taskDto.getStatus().equalsIgnoreCase(TaskStatus.IN_PROGRESS.toString())) {
                task.setStatus(TaskStatus.IN_PROGRESS.toString());
            }
            if(taskDto.getStatus().equalsIgnoreCase(TaskStatus.COMPLETED.toString())) {
                task.setStatus(TaskStatus.COMPLETED.toString());
            }else if(taskDto.getStatus().equalsIgnoreCase(TaskStatus.PENDING.toString())){
                task.setStatus(TaskStatus.PENDING.toString());
            }
            return taskRepository.save(task);
        } else {
            throw new TaskException(400, "Task not found for id: "+taskDto.getId());
        }
    }

    @Override
    public Task get(String id) {
        Optional<Task> OptTask = taskRepository.findById(id);
        if (OptTask.isPresent()) {
            return OptTask.get();
        } else {
            throw new TaskException(400, "Task not found for id: "+id);
        }
    }

    @Override
    public void delete(String id) {
        Optional<Task> OptTask = taskRepository.findById(id);
        if (OptTask.isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskException(400, "Task not found for id: " + id);
        }
    }

}

