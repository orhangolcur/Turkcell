package com.turkcell.library_cqrs_app.application.features.category.mapper;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.category.command.create.CreateCategoryCommand;
import com.turkcell.library_cqrs_app.application.features.category.command.create.CreateCategoryResponse;
import com.turkcell.library_cqrs_app.application.features.category.command.update.UpdateCategoryCommand;
import com.turkcell.library_cqrs_app.application.features.category.command.update.UpdateCategoryResponse;
import com.turkcell.library_cqrs_app.application.features.category.query.getall.GetAllCategoryResponse;
import com.turkcell.library_cqrs_app.application.features.category.query.getbyid.GetByIdCategoryResponse;
import com.turkcell.library_cqrs_app.domain.entity.Category;

@Component
public class CategoryMapper {
    
    public Category categoryFromCreateCommand(CreateCategoryCommand command) {
        Category category = new Category();
        category.setName(command.name());
        category.setDescription(command.description());
        return category;
    }

    public Category categoryFromUpdateCommand(Category category, UpdateCategoryCommand command) {
        category.setName(command.name());
        category.setDescription(command.description());
        return category;
    }

    public CreateCategoryResponse createResponseFromCategory(Category category) {
        return new CreateCategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }

    public UpdateCategoryResponse updateResponseFromCategory(Category category) {
        return new UpdateCategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }

    public GetAllCategoryResponse getAllResponseFromCategory(Category category) {
        return new GetAllCategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }

    public GetByIdCategoryResponse getByIdResponseFromCategory(Category category) {
        return new GetByIdCategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }
}
