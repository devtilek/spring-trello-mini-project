package practice.trello.DTO;

import lombok.Getter;
import lombok.Setter;
import practice.trello.Entity.TaskCategories;

import java.util.List;
@Getter
@Setter
public class FolderDTO {
    private Long id;
    private String name;
    private List<TaskCategories> taskCategoriesList;
}
