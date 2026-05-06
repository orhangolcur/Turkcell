package com.turkcell.library_cqrs_app.application.features.book.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.book.mapper.BookMapper;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BookRepository;

@Component
public class GetByIdQueryHandler implements QueryHandler<GetByIdBookQuery, GetByIdBookResponse> {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public GetByIdQueryHandler(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public GetByIdBookResponse handle(GetByIdBookQuery query) {
        return bookRepository.findById(query.id())
                .map(bookMapper::getByIdResponseFromBook)
                .orElseThrow(() -> new NotFoundException("Kitap bulunamadı"));
    }
}
