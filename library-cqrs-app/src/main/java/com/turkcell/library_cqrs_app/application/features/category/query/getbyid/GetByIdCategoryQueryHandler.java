package com.turkcell.library_cqrs_app.application.features.category.query.getbyid;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.CategoryRepository;

@Component
public class GetByIdCategoryQueryHandler implements QueryHandler<GetByIdCategoryQuery, GetByIdCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public GetByIdCategoryQueryHandler(
        CategoryRepository categoryRepository,
        CategoryMapper categoryMapper    
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public GetByIdCategoryResponse handle(GetByIdCategoryQuery query) {
        return categoryRepository.findById(query.id())
            .map(categoryMapper::getByIdResponseFromCategory)
            .orElseThrow(() -> new NotFoundException("Kategori bulunamadı."));
    }
}
