package com.turkcell.library_cqrs_app.application.features.bookcopy.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BookCopyRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdBookCopyQueryHandler implements QueryHandler<GetByIdBookCopyQuery, GetByIdBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper bookCopyMapper;

    public GetByIdBookCopyQueryHandler(
        BookCopyRepository bookCopyRepository,                               
        BookCopyMapper bookCopyMapper
    ) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyMapper = bookCopyMapper;
    }

    @Override
    public GetByIdBookCopyResponse handle(GetByIdBookCopyQuery query) {
        return bookCopyRepository.findById(query.id())
            .map(bookCopyMapper::getByIdResponseFromBookCopy)
            .orElseThrow(() -> new NotFoundException("Kitap kopyası bulunamadı."));
    }
}
