package com.turkcell.library_cqrs_app.application.features.bookcopy.command.create;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_cqrs_app.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_cqrs_app.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Book;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BookCopyRepository;

@Component
public class CreateBookCopyCommandHandler implements CommandHandler<CreateBookCopyCommand, CreateBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyBusinessRules bookCopyBusinessRules;
    private final BookBusinessRules bookBusinessRules;
    private final BranchBusinessRules branchBusinessRules;
    private final BookCopyMapper bookCopyMapper;

    public CreateBookCopyCommandHandler(
        BookCopyRepository bookCopyRepository,                                
        BookCopyBusinessRules bookCopyBusinessRules,
        BookBusinessRules bookBusinessRules,
        BranchBusinessRules branchBusinessRules,
        BookCopyMapper bookCopyMapper
    ) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyBusinessRules = bookCopyBusinessRules;
        this.bookBusinessRules = bookBusinessRules;
        this.branchBusinessRules = branchBusinessRules;
        this.bookCopyMapper = bookCopyMapper;
    }

    @Override
    public CreateBookCopyResponse handle(CreateBookCopyCommand command) {
        bookCopyBusinessRules.barcodeMustBeUnique(command.barcode());

        Book book = bookBusinessRules.getByIdOrThrow(command.bookId());
        Branch branch = branchBusinessRules.getByIdOrThrow(command.branchId());

        BookCopy bookCopy = bookCopyMapper.bookCopyFromCreateCommand(command);
        bookCopy.setBook(book);
        bookCopy.setBranch(branch);

        BookCopy saved = bookCopyRepository.save(bookCopy);
        return bookCopyMapper.createResponseFromBookCopy(saved);
    }
}
