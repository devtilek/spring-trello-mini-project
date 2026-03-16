package practice.trello.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.Entity.TaskCategories;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskCategoriesMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    TaskCategoryDTO toDTO(TaskCategories taskCategories);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    TaskCategories toEntity(TaskCategoryDTO taskCategoryDTO);

    List<TaskCategoryDTO> toDTOList(List<TaskCategories> taskCategoriesList);
}
