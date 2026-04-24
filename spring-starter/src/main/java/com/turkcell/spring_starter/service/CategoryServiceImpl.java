package com.turkcell.spring_starter.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository; 

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest request) {
        // Veritabanında insert-update çalıştır.
        // entity id'e sahipse update
        // entity id'si null ise insert

        Category category = new Category();
        category.setName(request.getName());

        this.categoryRepository.save(category);

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    public List<ListCategoryResponse> getAll() {
        List<Category> categories = this.categoryRepository.findAll();

        List<ListCategoryResponse> response = categories.stream().map(category -> {
            ListCategoryResponse listCategoryResponse = new ListCategoryResponse();
            listCategoryResponse.setId(category.getId());
            listCategoryResponse.setName(category.getName());
            return listCategoryResponse;
        }).toList();

        return response;
    }
}
