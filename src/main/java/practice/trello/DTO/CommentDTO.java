package practice.trello.DTO;

import lombok.Getter;
import lombok.Setter;
import practice.trello.Entity.Task;
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String comment;
    private Task task;
}
