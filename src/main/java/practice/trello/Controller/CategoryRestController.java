package practice.trello.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import practice.trello.DTO.TaskCategoryDTO;
import practice.trello.Service.TrelloService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final TrelloService service;

    @GetMapping
    public List<TaskCategoryDTO> getAll() {
        return service.getAllCategories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCategoryDTO create(@Valid @RequestBody TaskCategoryDTO category) {
        return service.addCat(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteCategory(id);
    }

    @PostMapping("/{categoryId}/folders/{folderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignToFolder(@PathVariable Long categoryId, @PathVariable Long folderId) {
        service.assignCategoryToFolder(folderId, categoryId);
    }

    @DeleteMapping("/{categoryId}/folders/{folderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromFolder(@PathVariable Long categoryId, @PathVariable Long folderId) {
        service.deleteCategoryFromFolder(folderId, categoryId);
    }
}
