package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.NoTaskFindException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks(){
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask", params = "id")
    public TaskDto getTask(@RequestParam("id") long taskId) throws NoTaskFindException {
        //Optional<Task> task = service.getTaskById(taskId);

        return taskMapper.mapToTaskDto(service.getTaskById(taskId).orElseThrow(NoTaskFindException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask", params = "id")
    public void deleteTask(@RequestParam("id") Long taskId) throws NoTaskFindException{
            service.getTaskById(taskId).orElseThrow(NoTaskFindException::new);
            service.deleteTask(taskId);

    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask( @RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }



}
