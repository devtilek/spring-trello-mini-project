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
    void deleteFolder(Long id);

    TaskDTO addTask(TaskDTO taskDTO);
    TaskDTO updateTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getAllTasksByFolderId(Long folderId);
    TaskDTO getTaskById(Long id);
    void deleteTask(Long id);

    void deleteCategoryFromFolder(Long folderId, Long categoryId);
    TaskCategoryDTO addCat(TaskCategoryDTO taskCategoriesDTO);
    List<TaskCategoryDTO> getAllCategories();
    void assignCategoryToFolder(Long folderId, Long categoryId);
    List<TaskCategoryDTO> toTaskCatsDTO(List<TaskCategories> taskCategoriesList);
    void deleteCategory(Long id);
}
