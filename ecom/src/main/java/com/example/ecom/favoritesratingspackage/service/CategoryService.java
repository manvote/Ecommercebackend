package com.example.Authentication.Service;

import com.example.Authentication.Entities.Category;
import com.example.Authentication.Entities.Subcategory;
import com.example.Authentication.Repositories.CategoryRepository;
import com.example.Authentication.Repositories.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final SubcategoryRepository subcategoryRepo;

    public CategoryService(CategoryRepository categoryRepo, SubcategoryRepository subcategoryRepo) {
        this.categoryRepo = categoryRepo;
        this.subcategoryRepo = subcategoryRepo;
    }

    // Category CRUD
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category updateCategory(Long id, Category newCat) {
        Category existing = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.name = newCat.name;
        existing.description = newCat.description;
        return categoryRepo.save(existing);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    // Subcategory CRUD
    public Subcategory addSubcategory(Long categoryId, Subcategory subcat) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        subcat.category = category;
        return subcategoryRepo.save(subcat);
    }

    public List<Subcategory> getSubcategories(Long categoryId) {
        return subcategoryRepo.findByCategoryId(categoryId);
    }

    public void deleteSubcategory(Long id) {
        subcategoryRepo.deleteById(id);
    }
}
