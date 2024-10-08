package uz.blessed.oson.entities;

import jakarta.persistence.*;
import lombok.Data;
import uz.blessed.oson.base.BaseEntity;
import uz.blessed.oson.enums.STATUS;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
public class Tasks extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private STATUS status;

    public static Tasks create(String title, String description, LocalDateTime dueDate, STATUS status) {
        Tasks task = new Tasks();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);
        return task;
    }
}
