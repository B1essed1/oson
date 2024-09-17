package uz.blessed.oson.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.blessed.oson.data.TaskFilter;
import uz.blessed.oson.data.req.TaskRequest;
import uz.blessed.oson.data.res.TaskResponse;
import uz.blessed.oson.service.TaskService;

@RestController
@RequestMapping("api/oson/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponse create(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable(value = "id" )Long id,@RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id,taskRequest);
    }

    @DeleteMapping("/{id}")
    public TaskResponse delete(@PathVariable(value = "id")Long id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public TaskResponse get(@PathVariable("id")Long id) {
        return taskService.getById(id);
    }

    @GetMapping("filter")
    public Page<TaskResponse> filter(@ModelAttribute TaskFilter filter) {
        return taskService.getAllTasks(filter);
    }
}
