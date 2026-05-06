package com.turkcell.library_cqrs_app.application.features.author.query.getall;

import java.util.List;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.mapper.AuthorMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class GetAllAuthorQueryHandler implements QueryHandler<GetAllAuthorQuery, List<GetAllAuthorResponse>> {

    private final AuthorRepository repository;
    private final AuthorMapper authorMapper;

    public GetAllAuthorQueryHandler(
        AuthorRepository repository,
        AuthorMapper authorMapper
    ) {
        this.repository = repository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<GetAllAuthorResponse> handle(GetAllAuthorQuery query) {
        return repository.findAll().stream()
                .map(authorMapper::getAllResponseFromAuthor)
                .toList();
    }
}
