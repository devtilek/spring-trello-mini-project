package practice.trello.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.trello.DTO.FolderDTO;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.DTO.TaskDTO;
import practice.trello.Service.TrelloService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final TrelloService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("folders", service.getAllFolders());
        return "index";
    }

    @PostMapping("addFolder")
    public String addFolderPost(@RequestParam("folder_name") String folderName) {
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setName(folderName);
        service.addFolder(folderDTO);
        return "redirect:/";
    }

    @GetMapping("folder/{id}")
    public String getFolder(@PathVariable Long id, Model model) {
        FolderDTO folderDTO = service.getFolderById(id);
        model.addAttribute("folder", folderDTO);

        List<TaskCategoryDTO> allCategories = service.getAllCategories();
        List<TaskCategoryDTO> myCategories = folderDTO.getTaskCategoriesList() == null
                ? List.of()
                : folderDTO.getTaskCategoriesList();

        List<TaskCategoryDTO> availableCategories = allCategories.stream()
                .filter(category -> myCategories.stream()
                        .noneMatch(myCategory -> myCategory.getId().equals(category.getId())))
                .collect(Collectors.toList());

        model.addAttribute("categories", availableCategories);
        model.addAttribute("tasks", service.getAllTasksByFolderId(id));
        return "folderDetails";
    }

    @PostMapping("/deleteCatFromFolder")
    public String deleteCatFromFolder(@RequestParam("cat_id") Long categoryId,
                                      @RequestParam("folder_id") Long folderId) {
        service.deleteCategoryFromFolder(folderId, categoryId);
        return "redirect:/folder/" + folderId;
    }

    @PostMapping("addCategory")
    public String addCategory(@RequestParam("cat_name") String categoryName) {
        TaskCategoryDTO category = new TaskCategoryDTO();
        category.setName(categoryName);
        service.addCat(category);
        return "redirect:/";
    }

    @PostMapping("/assignCategoryToFolder")
    public String assignCategoryToFolder(@RequestParam("cat_id") Long categoryId,
                                         @RequestParam("folder_id") Long folderId) {
        service.assignCategoryToFolder(folderId, categoryId);
        return "redirect:/folder/" + folderId;
    }

    @GetMapping("addCategory")
    public String addCategoryPage() {
        return "addCategory";
    }

    @PostMapping("addTask")
    public String addTask(@RequestParam("folder_id") Long folderId,
                          @RequestParam("title") String title,
                          @RequestParam("description") String description) {
        FolderDTO folderDTO = service.getFolderById(folderId);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setFolder(folderDTO);
        service.addTask(taskDTO);
        return "redirect:/folder/" + folderId;
    }

    @GetMapping("/folder/{folder_id}/{task_id}")
    public String getTask(@PathVariable("folder_id") Long folderId,
                          @PathVariable("task_id") Long taskId,
                          Model model) {
        TaskDTO taskDTO = service.getTaskById(taskId);
        model.addAttribute("task", taskDTO);
        model.addAttribute("folder", service.getFolderById(folderId));

        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(0, "TO DO");
        statuses.put(1, "IN TEST");
        statuses.put(2, "DONE");
        statuses.put(3, "FAILED");
        model.addAttribute("statuses", statuses);
        return "taskDetails";
    }

    @PostMapping("updateTask")
    public String updateTask(@RequestParam("folder_id") Long folderId,
                             @RequestParam("task_id") Long taskId,
                             @RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("status") int status) {
        TaskDTO taskDTO = service.getTaskById(taskId);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setFolder(service.getFolderById(folderId));
        taskDTO.setStatus(status);
        service.updateTask(taskDTO);
        return "redirect:/folder/" + folderId;
    }
}
