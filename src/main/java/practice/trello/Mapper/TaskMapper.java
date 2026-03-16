package practice.trello.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import practice.trello.DTO.TaskDTO;
import practice.trello.Entity.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "folder", target = "folder")
    TaskDTO toDTO(Task task);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "folder", target = "folder")
    Task toEntity(TaskDTO taskDTO);

    List<TaskDTO> toDTOList(List<Task> taskList);
}
