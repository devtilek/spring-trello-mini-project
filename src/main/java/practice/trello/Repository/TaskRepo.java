package practice.trello.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practice.trello.Entity.Comment;
import practice.trello.Entity.Task;

import java.util.List;

@Repository
@Transactional
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByFolderId (Long folder_id);
}
