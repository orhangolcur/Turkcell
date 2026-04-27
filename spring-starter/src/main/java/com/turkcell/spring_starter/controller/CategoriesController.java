package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.turkcell.spring_starter.dto.category.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.category.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.category.GetByIdCategoryResponse;
import com.turkcell.spring_starter.dto.category.ListCategoryResponse;
import com.turkcell.spring_starter.dto.category.UpdateCategoryRequest;
import com.turkcell.spring_starter.dto.category.UpdatedCategoryResponse;
import com.turkcell.spring_starter.service.CategoryServiceImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryService;

    public CategoriesController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public CreatedCategoryResponse create(@RequestBody CreateCategoryRequest request) {
        return this.categoryService.create(request);
    }
    
    @GetMapping
    public List<ListCategoryResponse> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdCategoryResponse getById(@PathVariable UUID id) {
        return this.categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCategoryResponse update(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {
        request.setId(id);
        return this.categoryService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.categoryService.delete(id);
    }

    @GetMapping("search")
    public List<ListCategoryResponse> getMethodName(@RequestParam String query) {
        return categoryService.search(query);
    }
}
