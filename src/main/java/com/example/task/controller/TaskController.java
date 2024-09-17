package com.example.task.controller;

import com.example.task.dto.ApiResponse;
import com.example.task.dto.TaskDto;
import com.example.task.entity.Task;
import com.example.task.exception.TaskException;
import com.example.task.service.TaskService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping()
    public Task create(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

//    @PutMapping
//    public ResponseEntity update (@RequestBody TaskDto taskDto) {
//        try {
//            ApiResponse<Task> response = ApiResponse.<Task>builder().statusCode(200).message("Success").data(taskService.update(taskDto)).error(null).build();
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (TaskException ex) {
//            ApiResponse response = ApiResponse.builder()
//                    .statusCode(ex.getStatusCode())
//                    .message("Error")
//                    .data(null)
//                    .error(ex.getMessage())
//                    .build();
//
//            return new ResponseEntity<>(response, HttpStatusCode.valueOf(ex.getStatusCode()));
//        }
//    }

    @PutMapping
    public ApiResponse update (@RequestBody TaskDto taskDto) {
        Task task = taskService.update(taskDto);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(200);
        apiResponse.setError(null);
        apiResponse.setMessage("Success");
        apiResponse.setData(task);
        return apiResponse;
//        return ApiResponse.<Task>builder().statusCode(200).message("Success").data(taskService.update(taskDto)).error(null).build();
    }

    @GetMapping("/{id}")
    public ApiResponse get(@PathVariable String id){
        /*Task task =  taskService.get(id);
        ApiResponse apiResponse =new ApiResponse();
        apiResponse.setStatusCode(200);
        apiResponse.setError(null);
        apiResponse.setMessage("Success");
        apiResponse.setData(task);
        return apiResponse;*/

        return ApiResponse.<Task>builder().statusCode(200).message("Success").data(taskService.get(id)).error(null).build();

    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id)  {
        return ApiResponse.<Task>builder().statusCode(200).message("Task deleted successfully").error(null).build();
    }

}
