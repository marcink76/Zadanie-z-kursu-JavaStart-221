package pl.javastart.zadanie_221c.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.zadanie_221c.model.Task;

@Repository
public interface TaskRepositoryInterface extends JpaRepository<Task, Long> {

}
