package com.turkcell.library_cqrs_app.application.features.book.command.create;

import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_cqrs_app.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.domain.entity.Book;
import com.turkcell.library_cqrs_app.domain.entity.Category;
import com.turkcell.library_cqrs_app.persistence.repository.BookRepository;

@Component
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, CreateBookResponse> {

    private final BookRepository bookRepository;
    private final BookBusinessRules bookBusinessRules;
    private final CategoryBusinessRules categoryBusinessRules;
    private final AuthorBusinessRules authorBusinessRules;

    public CreateBookCommandHandler(BookRepository bookRepository, BookBusinessRules bookBusinessRules,
            CategoryBusinessRules categoryBusinessRules, AuthorBusinessRules authorBusinessRules) {

        this.bookRepository = bookRepository;
        this.bookBusinessRules = bookBusinessRules;
        this.categoryBusinessRules = categoryBusinessRules;
        this.authorBusinessRules = authorBusinessRules;
    }

    @Override
    public CreateBookResponse handle(CreateBookCommand command) {

        bookBusinessRules.isbnMustBeUnique(command.isbn());

        Category category = categoryBusinessRules.getByIdOrThrow(command.categoryId());

        List<Author> authors = authorBusinessRules.getAllByIdsOrThrow(command.authorIds());

        Book book = new Book();
        book.setIsbn(command.isbn());
        book.setTitle(command.title());
        book.setStock(command.stock());
        book.setPublishYear(command.publishYear());
        book.setCategory(category);
        book.setAuthors(new HashSet<>(authors));

        Book savedBook = bookRepository.save(book);

        return new CreateBookResponse(
                savedBook.getId(),
                savedBook.getIsbn(),
                savedBook.getTitle(),
                savedBook.getStock(),
                savedBook.getPublishYear(),
                savedBook.getCategory().getName(),
                authors.stream().map(author -> author.getFirstName() + " " + author.getLastName()).toList());
    }

}
