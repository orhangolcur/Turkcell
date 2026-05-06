package com.turkcell.library_cqrs_app.application.features.book.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BookRepository;

@Component
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookCommand, DeleteBookResponse> {

    private final BookRepository bookRepository;
    private final BookBusinessRules bookBusinessRules;

    public DeleteBookCommandHandler(BookRepository bookRepository, BookBusinessRules bookBusinessRules) {
        this.bookRepository = bookRepository;
        this.bookBusinessRules = bookBusinessRules;
    }

    @Override
    public DeleteBookResponse handle(DeleteBookCommand command) {
        bookBusinessRules.bookMustExist(command.id());
        var book = bookBusinessRules.getByIdOrThrow(command.id());

        bookRepository.delete(book);

        return new DeleteBookResponse(
                command.id(),
                "Kitap başarıyla silindi");
    }

}
