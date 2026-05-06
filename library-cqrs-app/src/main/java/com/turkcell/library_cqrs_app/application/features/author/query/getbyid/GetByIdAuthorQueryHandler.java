package com.turkcell.library_cqrs_app.application.features.author.query.getbyid;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.mapper.AuthorMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class GetByIdAuthorQueryHandler implements QueryHandler<GetByIdAuthorQuery, GetByIdAuthorResponse> {

    private final AuthorRepository repository;
    private final AuthorMapper authorMapper;

    public GetByIdAuthorQueryHandler(
        AuthorRepository repository,
        AuthorMapper authorMapper
    ) {
        this.repository = repository;
        this.authorMapper = authorMapper;
    }

    @Override
    public GetByIdAuthorResponse handle(GetByIdAuthorQuery query) {
        return repository.findById(query.id())
                .map(authorMapper::getByIdResponseFromAuthor)
                .orElseThrow(() -> new NotFoundException("Yazar bulunamadı"));
    }
}
