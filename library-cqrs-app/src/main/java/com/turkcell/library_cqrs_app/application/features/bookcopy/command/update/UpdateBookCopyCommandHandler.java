package com.turkcell.library_cqrs_app.application.features.bookcopy.command.update;

import com.turkcell.library_cqrs_app.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_cqrs_app.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_cqrs_app.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Book;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BookCopyRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookCopyCommandHandler implements CommandHandler<UpdateBookCopyCommand, UpdateBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyBusinessRules bookCopyBusinessRules;
    private final BookBusinessRules bookBusinessRules;
    private final BranchBusinessRules branchBusinessRules;
    private final BookCopyMapper bookCopyMapper;

    public UpdateBookCopyCommandHandler(
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
    public UpdateBookCopyResponse handle(UpdateBookCopyCommand command) {
        BookCopy bookCopy = bookCopyBusinessRules.getByIdOrThrow(command.id());
        bookCopyBusinessRules.barcodeMustBeUniqueForUpdate(command.id(), command.barcode());

        Book book = bookBusinessRules.getByIdOrThrow(command.bookId());
        Branch branch = branchBusinessRules.getByIdOrThrow(command.branchId());

        bookCopyMapper.bookCopyFromUpdateCommand(bookCopy, command);
        bookCopy.setBook(book);
        bookCopy.setBranch(branch);

        BookCopy saved = bookCopyRepository.save(bookCopy);
        return bookCopyMapper.updateResponseFromBookCopy(saved);
    }
}
