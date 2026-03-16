package practice.trello.Service;

import practice.trello.DTO.FolderDTO;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.DTO.TaskDTO;
import practice.trello.Entity.TaskCategories;

import java.util.List;

public interface TrelloService {
    FolderDTO addFolder(FolderDTO folderDTO);
    List<FolderDTO> getAllFolders();
    FolderDTO getFolderById(Long id);
    TaskDTO addTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getAllTasksByFolderId(Long folder_id);
    void deleteCategoryFromFolder(Long folderId, Long categoryId);
    TaskDTO getTaskById(Long id);
    TaskCategoryDTO addCat(TaskCategoryDTO taskCategoriesDTO);
    List<TaskCategoryDTO> getAllCategories();
    void assignCategoryToFolder(Long folder_id, Long category_id);
    List<TaskCategoryDTO> toTaskCatsDTO(List<TaskCategories> taskCategoriesList);

}
