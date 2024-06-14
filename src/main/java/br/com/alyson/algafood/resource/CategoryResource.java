package br.com.alyson.algafood.resource;

import br.com.alyson.algafood.domain.entity.Category;
import br.com.alyson.algafood.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @Operation(summary = "Get a category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @Operation(summary = "Update an existing category")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryService.findById(id);
        if (category != null) {
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            return ResponseEntity.ok(categoryService.save(category));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
