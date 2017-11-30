package pl.javastart.zadanie_221c.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.zadanie_221c.model.Task;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class TaskPersistent {

    @Autowired
    TaskRepositoryInterface taskRepositoryInterface;

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Task task){
        entityManager.persist(task);
    }

    public List<Task> getAll(){
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t", Task.class);
        return query.getResultList();
    }
    public void updateStatus(long id){
        Task task = taskRepositoryInterface.findOne(id);
        task.setDoneStatus(true);
        entityManager.persist(task);
    }

}
