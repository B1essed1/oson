package uz.blessed.oson.service.implementation;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import uz.blessed.oson.data.TaskFilter;
import uz.blessed.oson.data.req.TaskRequest;
import uz.blessed.oson.data.res.TaskResponse;
import uz.blessed.oson.entities.Tasks;
import uz.blessed.oson.exception.ExceptionWithStatusCode;
import uz.blessed.oson.repositories.TaskRepository;
import uz.blessed.oson.service.TaskService;
import uz.blessed.oson.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskResponse taskResponse;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskResponse = new TaskResponse();
    }


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        var task = taskRequest.create();
        task = taskRepository.save(task);

        return getTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ExpressionException(404, "oson.task.not.found"));

        task = taskRequest.update(task);

        task = taskRepository.save(task);

        return getTaskResponse(task);
    }

    @Override
    public TaskResponse deleteTask(Long id) {

        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ExpressionException(404, "oson.task.not.found"));
        taskRepository.delete(task);

        return getTaskResponse(task);
    }

    @Override
    public Page<TaskResponse> getAllTasks(TaskFilter filter) {
        if (Objects.nonNull(filter.getDueDate())){
            if (Utils.isValidDate(filter.getDueDate())){
                filter.setDueLocalDateTime(Utils.convertToLocalDateTime(filter.getDueDate()));
            } else {
                throw new ExceptionWithStatusCode(400, "oson.task.duedate.not.valid");
            }
        }
        return taskRepository.findAll(getSpecifications(filter), filter.getPageable()).map(getTaskResponse()::convertToResponse);
    }

    @Override
    public TaskResponse getById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionWithStatusCode(400, "oson.task.not.found"));
        return getTaskResponse(task);
    }

    private static TaskResponse getTaskResponse(Tasks task) {
        return new TaskResponse().convertToResponse(task);
    }


    public Specification<Tasks> getSpecifications(TaskFilter filter) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filter.getTitle() + "%"));
            }
            if (filter.getDueLocalDateTime() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("dueDate"), filter.getDueLocalDateTime()));
            }

            if (filter.getStatus()!= null){
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public TaskResponse getTaskResponse() {
        return taskResponse;
    }
}
