package uz.blessed.oson.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uz.blessed.oson.entities.Tasks;
import uz.blessed.oson.enums.STATUS;
import uz.blessed.oson.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
     void saveTest() {

        // arrange
        Tasks task = Tasks.create("Test task", "desc", LocalDateTime.now(), STATUS.OPEN);
        var savedTask = taskRepository.save(task);
        Assertions.assertThat(savedTask.getId()).isNotNull();
        Assertions.assertThat(savedTask.getId()).isPositive();

    }

    @Test
    public void getAllTest() {

        Tasks task = Tasks.create("Test task", "desc", LocalDateTime.now(), STATUS.OPEN);
        Tasks task2 = Tasks.create("Test task 2", "desc 2", LocalDateTime.now(), STATUS.OPEN);
        taskRepository.save(task);
        taskRepository.save(task2);

        List<Tasks> savedTasks = taskRepository.findAll();

        Assertions.assertThat(savedTasks.size()).isNotNull();
        Assertions.assertThat(savedTasks.size()).isEqualTo(2);
    }

    @Test
    void deleteTest() {
        Tasks task = Tasks.create("Test delete", "desc", LocalDateTime.now(), STATUS.OPEN);
        taskRepository.save(task);
        taskRepository.delete(task);
        var deleted = taskRepository.findById(task.getId());
        Assertions.assertThat(deleted).isEmpty();

    }

    @Test
    void updateTest() {

        Tasks task = Tasks.create("Test update", "desc", LocalDateTime.now(), STATUS.OPEN);
        taskRepository.save(task);
        task.setTitle("Test updated successfully");
        task.setStatus(STATUS.COMPLETED);
        var updatedTask = taskRepository.save(task);

        Assertions.assertThat(updatedTask.getId()).isNotNull();
        Assertions.assertThat(updatedTask.getStatus()).isEqualTo(STATUS.COMPLETED);
        Assertions.assertThat(updatedTask.getTitle()).isEqualTo("Test updated successfully");

    }

    @Test
     void findByIdTest() {
        Tasks task = Tasks.create("Test task", "desc", LocalDateTime.now(), STATUS.OPEN);
        taskRepository.save(task);
        Tasks savedTask = taskRepository.findById(task.getId()).get();
        Assertions.assertThat(savedTask.getId()).isNotNull();
    }
}
