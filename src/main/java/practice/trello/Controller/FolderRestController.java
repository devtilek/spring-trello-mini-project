package practice.trello.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import practice.trello.DTO.FolderDTO;
import practice.trello.DTO.TaskDTO;
import practice.trello.Service.TrelloService;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderRestController {

    private final TrelloService service;

    @GetMapping
    public List<FolderDTO> getAll() {
        return service.getAllFolders();
    }

    @GetMapping("/{id}")
    public FolderDTO getById(@PathVariable Long id) {
        return service.getFolderById(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasks(@PathVariable Long id) {
        return service.getAllTasksByFolderId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderDTO create(@Valid @RequestBody FolderDTO folder) {
        return service.addFolder(folder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteFolder(id);
    }
}
