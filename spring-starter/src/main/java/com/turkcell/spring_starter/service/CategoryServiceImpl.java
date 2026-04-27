package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.turkcell.spring_starter.dto.category.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.category.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.category.GetByIdCategoryResponse;
import com.turkcell.spring_starter.dto.category.ListCategoryResponse;
import com.turkcell.spring_starter.dto.category.UpdateCategoryRequest;
import com.turkcell.spring_starter.dto.category.UpdatedCategoryResponse;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.repository.CategoryRepository;

import jakarta.persistence.EntityManager;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository; 
    private final EntityManager entityManager;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
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

    public GetByIdCategoryResponse getById(UUID id) {
        Category category = this.categoryRepository.findById(id).orElseThrow();

        GetByIdCategoryResponse response = new GetByIdCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    public UpdatedCategoryResponse update(UpdateCategoryRequest request) {
        Category category = this.categoryRepository.findById(request.getId()).orElseThrow();
        category.setName(request.getName());

        this.categoryRepository.save(category);

        UpdatedCategoryResponse response = new UpdatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    public void delete(UUID id) {
        Category category = this.categoryRepository.findById(id).orElseThrow();

        this.categoryRepository.delete(category);
    }

    public List<ListCategoryResponse> search(String query)
    {
        //Set<Category> categories = categoryRepository.findByNameLike("%" + query + "%");

        // String Concatination -> KESİNLİKLE YASAK, SQL injection riski var
        //String jpql = "Select c from Category c Where c.name LIKE '%" + query + "%'";

        String jpql = "Select c from Category c Where c.name like :query";

        // Entity Manager SQL Injection riskine karşı gelen query'i kontrol eder ve sadece istenen formatta veri gelmesini sağlar
        List<Category> categories = entityManager
        .createQuery(jpql, Category.class)
        .setParameter("query", "%" + query + "%")
        .getResultList();

        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

}
