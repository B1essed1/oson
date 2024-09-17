package uz.blessed.oson.data.req;

import lombok.Data;
import uz.blessed.oson.entities.Tasks;
import uz.blessed.oson.enums.STATUS;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private STATUS status;
    private LocalDateTime dueDate;

    public Tasks create() {
        Tasks tasks = new Tasks();
        tasks.setTitle(getTitle());
        tasks.setDescription(getDescription());

        if ((getStatus() != null)) {
            tasks.setStatus(getStatus());
        } else {
            tasks.setStatus(STATUS.OPEN);
        }

        tasks.setDueDate(getDueDate());
        return tasks;
    }

    public Tasks update(Tasks tasks) {
       if(Objects.nonNull(getStatus())) tasks.setStatus(getStatus());
       if(Objects.nonNull(getDueDate())) tasks.setDueDate(getDueDate());
       if(Objects.nonNull(getTitle())) tasks.setTitle(getTitle());
       if(Objects.nonNull(getDescription())) tasks.setDescription(getDescription());
       return tasks;
    }
}
