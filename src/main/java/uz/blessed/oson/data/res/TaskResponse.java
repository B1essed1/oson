package uz.blessed.oson.data.res;

import lombok.Data;
import uz.blessed.oson.entities.Tasks;
import uz.blessed.oson.enums.STATUS;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private STATUS status;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;

    public TaskResponse convertToResponse(Tasks entity){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(entity.getId());
        taskResponse.setTitle(entity.getTitle());
        taskResponse.setDescription(entity.getDescription());
        taskResponse.setStatus(entity.getStatus());
        taskResponse.setCreatedDate(entity.getCreatedDate());
        taskResponse.setDueDate(entity.getDueDate());
        return taskResponse;
    }
}
