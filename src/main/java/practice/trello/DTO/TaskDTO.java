package practice.trello.DTO;

import lombok.Getter;
import lombok.Setter;
import practice.trello.Entity.Folder;
@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private int status;
    private FolderDTO folder;
}
