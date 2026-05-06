package com.turkcell.library_cqrs_app.application.features.category.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, UpdateCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;

    public UpdateCategoryCommandHandler(CategoryRepository categoryRepository,
            CategoryBusinessRules categoryBusinessRules) {
        this.categoryRepository = categoryRepository;
        this.categoryBusinessRules = categoryBusinessRules;
    }

    @Override
    public UpdateCategoryResponse handle(UpdateCategoryCommand command) {
        var category = categoryBusinessRules.getByIdOrThrow(command.id());

        categoryBusinessRules.categoryNameMustBeUniqueForUpdate(command.id(), command.name());

        category.setName(command.name());
        category.setDescription(command.description());

        categoryRepository.save(category);

        return new UpdateCategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription());
    }

}
