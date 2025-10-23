package com.example.Authentication.Controller;

import com.example.Authentication.Entities.Category;
import com.example.Authentication.Entities.Subcategory;
import com.example.Authentication.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // Category routes
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return service.addCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return service.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
    }

    // Subcategory routes
    @PostMapping("/{categoryId}/subcategories")
    public Subcategory addSubcategory(@PathVariable Long categoryId, @RequestBody Subcategory subcat) {
        return service.addSubcategory(categoryId, subcat);
    }

    @GetMapping("/{categoryId}/subcategories")
    public List<Subcategory> getSubcategories(@PathVariable Long categoryId) {
        return service.getSubcategories(categoryId);
    }

    @DeleteMapping("/subcategories/{id}")
    public void deleteSubcategory(@PathVariable Long id) {
        service.deleteSubcategory(id);
    }
}
