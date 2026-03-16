package practice.trello.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.trello.DTO.FolderDTO;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.DTO.TaskDTO;
import practice.trello.Entity.Folder;
import practice.trello.Entity.Task;
import practice.trello.Entity.TaskCategories;
import practice.trello.Mapper.CommentMapper;
import practice.trello.Mapper.FolderMapper;
import practice.trello.Mapper.TaskCategoriesMapper;
import practice.trello.Mapper.TaskMapper;
import practice.trello.Repository.CommentRepo;
import practice.trello.Repository.FolderRepo;
import practice.trello.Repository.TaskCategoryRepo;
import practice.trello.Repository.TaskRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrelloServiceImpl implements TrelloService{

    private final CommentRepo commentRepo;
    private final TaskRepo taskRepo;
    private final FolderRepo folderRepo;
    private final TaskCategoryRepo taskCategoryRepo;

    private final CommentMapper commentMapper;
    private final TaskMapper taskMapper;
    private final FolderMapper folderMapper;
    private final TaskCategoriesMapper taskCategoriesMapper;

    @Override
    public FolderDTO addFolder(FolderDTO folderDTO) {
        Folder folder = folderMapper.toEntity(folderDTO);
        return folderMapper.toDTO(folderRepo.save(folder));
    }

    @Override
    public List<FolderDTO> getAllFolders() {
        List<Folder> folderList = folderRepo.findAll();
        return folderMapper.toDTOList(folderList);
    }

    @Override
    public FolderDTO getFolderById(Long id) {
        Folder folder = folderRepo.findById(id).orElse(null);
        return folderMapper.toDTO(folder);
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        return taskMapper.toDTO(taskRepo.save(task));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> taskList = taskRepo.findAll();
        return taskMapper.toDTOList(taskList);
    }

    @Override
    public List<TaskDTO> getAllTasksByFolderId(Long folder_id) {
        List<Task> taskList = taskRepo.findByFolderId(folder_id);
        return taskMapper.toDTOList(taskList);
    }

    @Override
    public void deleteCategoryFromFolder(Long folderId, Long categoryId) {
        Folder folder = folderRepo.findById(folderId).orElse(null);
        TaskCategories taskCategory = taskCategoryRepo.findById(categoryId).orElse(null);
        if (folder != null && taskCategory != null){
            List<TaskCategories> taskCategoriesList = folder.getTaskCategoriesList();
            if (taskCategoriesList == null){
                taskCategoriesList = new ArrayList<>();
            }

            taskCategoriesList.remove(taskCategory);
            folder.setTaskCategoriesList(taskCategoriesList);
            folderRepo.save(folder);
        }
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElse(null);
        return taskMapper.toDTO(task);
    }

    @Override
    public TaskCategoryDTO addCat(TaskCategoryDTO taskCategoriesDTO) {
        TaskCategories taskCat = taskCategoriesMapper.toEntity(taskCategoriesDTO);
        return taskCategoriesMapper.toDTO(taskCategoryRepo.save(taskCat));
    }

    @Override
    public List<TaskCategoryDTO> getAllCategories() {
        List<TaskCategories> taskCategoriesList = taskCategoryRepo.findAll();
        return  taskCategoriesMapper.toDTOList(taskCategoriesList);
    }

    @Override
    public void assignCategoryToFolder(Long folder_id, Long category_id) {
        Folder folder = folderRepo.findById(folder_id).orElse(null);
        TaskCategories taskCategory = taskCategoryRepo.findById(category_id).orElse(null);
        if (folder != null && taskCategory != null){
            List<TaskCategories> taskCategoriesList = folder.getTaskCategoriesList();
            if (taskCategoriesList == null){
                taskCategoriesList = new ArrayList<>();
            }

            taskCategoriesList.add(taskCategory);
            folder.setTaskCategoriesList(taskCategoriesList);
            folderRepo.save(folder);
        }
    }

    @Override
    public List<TaskCategoryDTO> toTaskCatsDTO(List<TaskCategories> taskCategoriesList) {
        return taskCategoriesMapper.toDTOList(taskCategoriesList);
    }
}
