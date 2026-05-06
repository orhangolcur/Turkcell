package com.turkcell.library_cqrs_app.application.features.category.query.getall;

import java.util.List;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class GetAllCategoryQueryHandler implements QueryHandler<GetAllCategoryQuery, List<GetAllCategoryResponse>> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public GetAllCategoryQueryHandler(
        CategoryRepository categoryRepository, 
        CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<GetAllCategoryResponse> handle(GetAllCategoryQuery query) {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::getAllResponseFromCategory)
                .toList();
    }
}
