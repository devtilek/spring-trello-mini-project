package practice.trello.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practice.trello.Entity.Comment;

@Repository
@Transactional
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
