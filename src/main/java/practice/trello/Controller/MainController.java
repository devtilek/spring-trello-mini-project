package practice.trello.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.trello.DTO.FolderDTO;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.DTO.TaskDTO;
import practice.trello.Service.TrelloService;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class MainController {

    private final TrelloService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("folders", service.getAllFolders());
        return "index";
    }

    @PostMapping(value = "addFolder")
    public String addFolderPost(
            @RequestParam(name = "folder_name") String folderName
    ){
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setName(folderName);
        service.addFolder(folderDTO);
        return "redirect:/";
    }

    @GetMapping("folder/{id}")
    public String getFolder(
            @PathVariable(name = "id") Long id,
            Model model

    ){
        FolderDTO folderDTO = service.getFolderById(id);
        model.addAttribute("folder", folderDTO);
        List<TaskCategoryDTO> taskCategoryDTOList = service.getAllCategories();
        List<TaskCategoryDTO> myCats = service.toTaskCatsDTO(folderDTO.getTaskCategoriesList());
        List<TaskCategoryDTO> result = taskCategoryDTOList.stream()
                        .filter(cat -> myCats.stream().noneMatch(
                                myCat -> myCat.getId().equals(cat.getId())
                        )).collect(Collectors.toList());

        model.addAttribute("categories", result);
        model.addAttribute("tasks", service.getAllTasksByFolderId(id));
        return "folderDetails";
    }

    @PostMapping("/deleteCatFromFolder")
    public String deleteCatFromFolder(
            @RequestParam(name = "cat_id") Long cat_id,
            @RequestParam(name = "folder_id") Long folder_id
    ){
        service.deleteCategoryFromFolder(folder_id, cat_id);
        return "redirect:/folder/" + folder_id;
    }

    @PostMapping(value = "addCategory")
    public String addCategory(
            @RequestParam(name = "cat_name") String cat_name
    ){
        TaskCategoryDTO taskCategoryDTO = new TaskCategoryDTO();
        taskCategoryDTO.setName(cat_name);
        service.addCat(taskCategoryDTO);
        return "redirect:/";
    }

    @PostMapping(value = "/assignCategoryToFolder")
    public String assignCategoryToFolder(
            @RequestParam(name = "cat_id") Long cat_id,
            @RequestParam(name = "folder_id") Long folder_id
    ){
        service.assignCategoryToFolder(folder_id, cat_id);
        return "redirect:/folder/" + folder_id;
    }

    @GetMapping("addCategory")
    public String addCategoryPage(){
        return "addCategory";
    }

    @PostMapping("addTask")
    public String addTask(
            @RequestParam(name = "folder_id") Long folder_id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description
    ){
        FolderDTO folderDTO = service.getFolderById(folder_id);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setFolder(folderDTO);
        service.addTask(taskDTO);
        return "redirect:/folder/" + folder_id;
    }

    @GetMapping("/folder/{folder_id}/{task_id}")
    public String getTask(
            @PathVariable(name = "folder_id") Long folder_id,
            @PathVariable(name = "task_id") Long task_id,
            Model model
    ){
        TaskDTO taskDTO = service.getTaskById(task_id);
        model.addAttribute("task", taskDTO);
        FolderDTO folderDTO = service.getFolderById(folder_id);
        model.addAttribute("folder", folderDTO);
        HashMap<Long, String> status = new HashMap<>();
        status.put(0L, "TO DO");
        status.put(1L, "IN TEST");
        status.put(2L, "DONE");
        status.put(3L, "FAILED");
        model.addAttribute("statuses", status);
        return "taskDetails";
    }

    @PostMapping("updateTask")
    public String updateTask(
            @RequestParam(name = "folder_id") Long folder_id,
            @RequestParam(name = "task_id") Long task_id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "status") int status
    ){
        FolderDTO folderDTO = service.getFolderById(folder_id);
        TaskDTO taskDTO = service.getTaskById(task_id);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setFolder(folderDTO);
        taskDTO.setStatus(status);
        service.addTask(taskDTO);
        return "redirect:/folder/" + folder_id;
    }
}
