package practice.trello.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import practice.trello.DTO.FolderDTO;
import practice.trello.Entity.Folder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FolderMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "taskCategoriesList", target = "taskCategoriesList")
    FolderDTO toDTO(Folder folder);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "taskCategoriesList", target = "taskCategoriesList")
    Folder toEntity(FolderDTO folderDTO);

    List<FolderDTO> toDTOList(List<Folder> folderList);

}
