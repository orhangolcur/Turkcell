package com.turkcell.library_cqrs_app.application.features.category.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class DeleteCategoryCommandHandler implements CommandHandler<DeleteCategoryCommand, DeleteCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;

    public DeleteCategoryCommandHandler(CategoryRepository categoryRepository,
            CategoryBusinessRules categoryBusinessRules) {
        this.categoryRepository = categoryRepository;
        this.categoryBusinessRules = categoryBusinessRules;
    }

    @Override
    public DeleteCategoryResponse handle(DeleteCategoryCommand command) {
        var category = categoryBusinessRules.getByIdOrThrow(command.id());

        categoryRepository.delete(category);

        return new DeleteCategoryResponse(
                category.getId(),
                "Kategori başarıyla silindi");
    }

}
