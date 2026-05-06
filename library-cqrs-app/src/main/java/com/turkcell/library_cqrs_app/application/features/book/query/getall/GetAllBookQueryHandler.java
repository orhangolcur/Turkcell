package com.turkcell.library_cqrs_app.application.features.book.query.getall;

import java.util.List;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.book.mapper.BookMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BookRepository;

@Component
public class GetAllBookQueryHandler implements QueryHandler<GetAllBookQuery, List<GetAllBookResponse>> {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public GetAllBookQueryHandler(
        BookRepository bookRepository,
        BookMapper bookMapper
    ) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<GetAllBookResponse> handle(GetAllBookQuery query) {
        return bookRepository.findAll().stream()
                .map(bookMapper::getAllResponseFromBook)
                .toList();
    }
}
