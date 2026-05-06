package com.turkcell.library_cqrs_app.application.features.category.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Category;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, CreateCategoryResponse> {

    private final CategoryRepository repository;
    private final CategoryBusinessRules categoryBusinessRules;

    public CreateCategoryCommandHandler(CategoryRepository repository, CategoryBusinessRules categoryBusinessRules) {
        this.repository = repository;
        this.categoryBusinessRules = categoryBusinessRules;
    }

    @Override
    public CreateCategoryResponse handle(CreateCategoryCommand command) {
        categoryBusinessRules.categoryNameMustBeUnique(command.name());

        Category category = new Category();
        category.setName(command.name());
        category.setDescription(command.description());

        repository.save(category);

        return new CreateCategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription());
    }
}
