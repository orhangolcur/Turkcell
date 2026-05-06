package com.turkcell.library_cqrs_app.application.features.category.command.update;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Category;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, UpdateCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;
    private final CategoryMapper categoryMapper;

    public UpdateCategoryCommandHandler(
        CategoryRepository categoryRepository,
        CategoryBusinessRules categoryBusinessRules, 
        CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryBusinessRules = categoryBusinessRules;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public UpdateCategoryResponse handle(UpdateCategoryCommand command) {
        Category category = categoryBusinessRules.getByIdOrThrow(command.id());

        categoryBusinessRules.categoryNameMustBeUniqueForUpdate(command.id(), command.name());

        categoryMapper.categoryFromUpdateCommand(category, command);

        Category saved = categoryRepository.save(category);

        return categoryMapper.updateResponseFromCategory(saved);
    }
}
