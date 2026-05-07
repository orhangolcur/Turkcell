package com.turkcell.library_cqrs_app.application.features.bookcopy.command.delete;

import com.turkcell.library_cqrs_app.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;
import com.turkcell.library_cqrs_app.persistence.repository.BookCopyRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookCopyCommandHandler implements CommandHandler<DeleteBookCopyCommand, DeleteBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyBusinessRules bookCopyBusinessRules;

    public DeleteBookCopyCommandHandler(
        BookCopyRepository bookCopyRepository,                                
        BookCopyBusinessRules bookCopyBusinessRules
    ) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyBusinessRules = bookCopyBusinessRules;
    }

    @Override
    public DeleteBookCopyResponse handle(DeleteBookCopyCommand command) {
        BookCopy bookCopy = bookCopyBusinessRules.getByIdOrThrow(command.id());
        bookCopyRepository.delete(bookCopy);

        return new DeleteBookCopyResponse(
            command.id(),
            "Kitap kopyası başarıyla silindi."
        );
    }
}
