package uz.blessed.oson.service;

import org.springframework.data.domain.Page;
import uz.blessed.oson.data.TaskFilter;
import uz.blessed.oson.data.req.TaskRequest;
import uz.blessed.oson.data.res.TaskResponse;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    TaskResponse deleteTask(Long id);
    Page<TaskResponse> getAllTasks(TaskFilter filter);
    TaskResponse getById(Long id);
}
