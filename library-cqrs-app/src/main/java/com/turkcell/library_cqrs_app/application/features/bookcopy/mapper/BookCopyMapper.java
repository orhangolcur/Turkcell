package com.turkcell.library_cqrs_app.application.features.bookcopy.mapper;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.create.CreateBookCopyCommand;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.create.CreateBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.update.UpdateBookCopyCommand;
import com.turkcell.library_cqrs_app.application.features.bookcopy.command.update.UpdateBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.query.getall.GetAllBookCopyResponse;
import com.turkcell.library_cqrs_app.application.features.bookcopy.query.getbyid.GetByIdBookCopyResponse;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;

@Component
public class BookCopyMapper {

    public BookCopy bookCopyFromCreateCommand(CreateBookCopyCommand command) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBarcode(command.barcode());
        bookCopy.setStatus(command.status());
        return bookCopy;
    }

    public BookCopy bookCopyFromUpdateCommand(BookCopy bookCopy, UpdateBookCopyCommand command) {
        bookCopy.setBarcode(command.barcode());
        bookCopy.setStatus(command.status());
        return bookCopy;
    }

    public CreateBookCopyResponse createResponseFromBookCopy(BookCopy bookCopy) {
        return new CreateBookCopyResponse(
            bookCopy.getId(),
            bookCopy.getBarcode(),
            bookCopy.getStatus(),
            bookCopy.getBook().getTitle(),
            bookCopy.getBranch().getName()
        );
    }

    public UpdateBookCopyResponse updateResponseFromBookCopy(BookCopy bookCopy) {
        return new UpdateBookCopyResponse(
            bookCopy.getId(),
            bookCopy.getBarcode(),
            bookCopy.getStatus(),
            bookCopy.getBook().getTitle(),
            bookCopy.getBranch().getName()
        );
    }

    public GetAllBookCopyResponse getAllResponseFromBookCopy(BookCopy bookCopy) {
        return new GetAllBookCopyResponse(
            bookCopy.getId(),
            bookCopy.getBarcode(),
            bookCopy.getStatus(),
            bookCopy.getBook().getTitle(),
            bookCopy.getBranch().getName()
        );
    }

    public GetByIdBookCopyResponse getByIdResponseFromBookCopy(BookCopy bookCopy) {
        return new GetByIdBookCopyResponse(
            bookCopy.getId(),
            bookCopy.getBarcode(),
            bookCopy.getStatus(),
            bookCopy.getBook().getTitle(),
            bookCopy.getBranch().getName()
        );
    }

}
