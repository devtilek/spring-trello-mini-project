package practice.trello.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private Long id;

    @NotBlank(message = "Task title is required")
    @Size(max = 200, message = "Task title must not exceed 200 characters")
    private String title;

    @Size(max = 5000, message = "Task description must not exceed 5000 characters")
    private String description;

    private int status;
    private FolderDTO folder;
}
