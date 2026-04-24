package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.service.CategoryServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryService;

    public CategoriesController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public void create(@RequestBody Category category) {
        this.categoryService.create(category);
    }
    

}
