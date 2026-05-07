package com.turkcell.library_cqrs_app.application.features.bookcopy.query.getall;

import com.turkcell.library_cqrs_app.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BookCopyRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllBookCopyQueryHandler implements QueryHandler<GetAllBookCopyQuery, List<GetAllBookCopyResponse>> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper bookCopyMapper;

    public GetAllBookCopyQueryHandler(
        BookCopyRepository bookCopyRepository,                              
        BookCopyMapper bookCopyMapper
    ) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyMapper = bookCopyMapper;
    }

    @Override
    public List<GetAllBookCopyResponse> handle(GetAllBookCopyQuery query) {
        return bookCopyRepository.findAll().stream()
            .map(bookCopyMapper::getAllResponseFromBookCopy)
            .toList();
    }
}
