package com.turkcell.library_cqrs_app.application.features.category.command.create;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Category;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, CreateCategoryResponse> {

    private final CategoryRepository repository;
    private final CategoryBusinessRules categoryBusinessRules;
    private final CategoryMapper categoryMapper;

    public CreateCategoryCommandHandler(
        CategoryRepository repository, 
        CategoryBusinessRules categoryBusinessRules,
        CategoryMapper categoryMapper
    ) {
        this.repository = repository;
        this.categoryBusinessRules = categoryBusinessRules;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CreateCategoryResponse handle(CreateCategoryCommand command) {
        categoryBusinessRules.categoryNameMustBeUnique(command.name());

        Category category = categoryMapper.categoryFromCreateCommand(command);

        Category savedCategory = repository.save(category);

        return categoryMapper.createResponseFromCategory(savedCategory);
    }
}
