package practice.trello.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.trello.DTO.FolderDTO;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.DTO.TaskDTO;
import practice.trello.Entity.Folder;
import practice.trello.Entity.Task;
import practice.trello.Entity.TaskCategories;
import practice.trello.Exception.ResourceNotFoundException;
import practice.trello.Mapper.FolderMapper;
import practice.trello.Mapper.TaskCategoriesMapper;
import practice.trello.Mapper.TaskMapper;
import practice.trello.Repository.FolderRepo;
import practice.trello.Repository.TaskCategoryRepo;
import practice.trello.Repository.TaskRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrelloServiceImpl implements TrelloService {

    private final TaskRepo taskRepo;
    private final FolderRepo folderRepo;
    private final TaskCategoryRepo taskCategoryRepo;

    private final TaskMapper taskMapper;
    private final FolderMapper folderMapper;
    private final TaskCategoriesMapper taskCategoriesMapper;

    @Override
    @Transactional
    public FolderDTO addFolder(FolderDTO folderDTO) {
        Folder folder = folderMapper.toEntity(folderDTO);
        return folderMapper.toDTO(folderRepo.save(folder));
    }

    @Override
    public List<FolderDTO> getAllFolders() {
        return folderMapper.toDTOList(folderRepo.findAll());
    }

    @Override
    public FolderDTO getFolderById(Long id) {
        return folderMapper.toDTO(findFolder(id));
    }

    @Override
    @Transactional
    public void deleteFolder(Long id) {
        folderRepo.delete(findFolder(id));
    }

    @Override
    @Transactional
    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        if (taskDTO.getFolder() == null || taskDTO.getFolder().getId() == null) {
            throw new ResourceNotFoundException("Folder is required for a task");
        }
        task.setFolder(findFolder(taskDTO.getFolder().getId()));
        return taskMapper.toDTO(taskRepo.save(task));
    }

    @Override
    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO) {
        if (taskDTO.getId() == null) {
            throw new ResourceNotFoundException("Task id is required for update");
        }
        Task existing = findTask(taskDTO.getId());
        Task updated = taskMapper.toEntity(taskDTO);
        updated.setId(existing.getId());
        if (taskDTO.getFolder() == null || taskDTO.getFolder().getId() == null) {
            throw new ResourceNotFoundException("Folder is required for a task");
        }
        updated.setFolder(findFolder(taskDTO.getFolder().getId()));
        return taskMapper.toDTO(taskRepo.save(updated));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskMapper.toDTOList(taskRepo.findAll());
    }

    @Override
    public List<TaskDTO> getAllTasksByFolderId(Long folderId) {
        findFolder(folderId);
        return taskMapper.toDTOList(taskRepo.findByFolderId(folderId));
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        return taskMapper.toDTO(findTask(id));
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskRepo.delete(findTask(id));
    }

    @Override
    @Transactional
    public void deleteCategoryFromFolder(Long folderId, Long categoryId) {
        Folder folder = findFolder(folderId);
        TaskCategories category = findCategory(categoryId);
        List<TaskCategories> categories = folder.getTaskCategoriesList();
        if (categories != null) {
            categories.remove(category);
        }
    }

    @Override
    @Transactional
    public TaskCategoryDTO addCat(TaskCategoryDTO taskCategoriesDTO) {
        TaskCategories category = taskCategoriesMapper.toEntity(taskCategoriesDTO);
        return taskCategoriesMapper.toDTO(taskCategoryRepo.save(category));
    }

    @Override
    public List<TaskCategoryDTO> getAllCategories() {
        return taskCategoriesMapper.toDTOList(taskCategoryRepo.findAll());
    }

    @Override
    @Transactional
    public void assignCategoryToFolder(Long folderId, Long categoryId) {
        Folder folder = findFolder(folderId);
        TaskCategories category = findCategory(categoryId);
        List<TaskCategories> categories = folder.getTaskCategoriesList();
        if (categories == null) {
            categories = new ArrayList<>();
            folder.setTaskCategoriesList(categories);
        }
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    @Override
    public List<TaskCategoryDTO> toTaskCatsDTO(List<TaskCategories> taskCategoriesList) {
        return taskCategoriesMapper.toDTOList(taskCategoriesList == null ? List.of() : taskCategoriesList);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        taskCategoryRepo.delete(findCategory(id));
    }

    private Folder findFolder(Long id) {
        return folderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found: " + id));
    }

    private Task findTask(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
    }

    private TaskCategories findCategory(Long id) {
        return taskCategoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    }
}
