package uz.blessed.oson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.blessed.oson.entities.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long>, JpaSpecificationExecutor<Tasks> {
}
