package practice.trello.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import practice.trello.Entity.TaskCategories;

import java.util.List;

@Getter
@Setter
public class FolderDTO {
    private Long id;

    @NotBlank(message = "Folder name is required")
    @Size(max = 100, message = "Folder name must not exceed 100 characters")
    private String name;

    private List<TaskCategories> taskCategoriesList;
}
