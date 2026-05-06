package com.turkcell.library_cqrs_app.application.features.book.command.update;

import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.domain.entity.Category;
import com.turkcell.library_cqrs_app.persistence.repository.BookRepository;

@Component
public class UpdateBookCommandHandler implements CommandHandler<UpdateBookCommand, UpdateBookResponse> {

    private final BookRepository bookRepository;
    private final BookBusinessRules bookBusinessRules;
    private final CategoryBusinessRules categoryBusinessRules;
    private final AuthorBusinessRules authorBusinessRules;

    public UpdateBookCommandHandler(BookRepository bookRepository, BookBusinessRules bookBusinessRules,
            CategoryBusinessRules categoryBusinessRules, AuthorBusinessRules authorBusinessRules) {
        this.bookRepository = bookRepository;
        this.bookBusinessRules = bookBusinessRules;
        this.categoryBusinessRules = categoryBusinessRules;
        this.authorBusinessRules = authorBusinessRules;
    }

    @Override
    public UpdateBookResponse handle(UpdateBookCommand command) {
        var book = bookBusinessRules.getByIdOrThrow(command.id());
        bookBusinessRules.isbnMustBeUniqueForUpdate(command.id(), command.isbn());

        Category category = categoryBusinessRules.getByIdOrThrow(command.categoryId());

        List<Author> authors = authorBusinessRules.getAllByIdsOrThrow(command.authorIds());

        book.setTitle(command.title());
        book.setIsbn(command.isbn());
        book.setStock(command.stock());
        book.setPublishYear(command.publishYear());
        book.setCategory(category);
        book.setAuthors(new HashSet<>(authors));
        bookRepository.save(book);

        return new UpdateBookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getStock(),
                book.getPublishYear(),
                book.getCategory() != null ? book.getCategory().getName() : null,
                authors.stream().map(author -> author.getFirstName() + " " + author.getLastName()).toList());
    }
}
