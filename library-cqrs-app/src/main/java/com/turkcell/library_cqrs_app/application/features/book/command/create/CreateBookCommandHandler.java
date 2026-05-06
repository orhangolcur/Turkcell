package com.turkcell.library_cqrs_app.application.features.book.command.create;

import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.application.features.book.mapper.BookMapper;
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
    private final BookMapper bookMapper;

    public CreateBookCommandHandler(
        BookRepository bookRepository, 
        BookBusinessRules bookBusinessRules,
        CategoryBusinessRules categoryBusinessRules, 
        AuthorBusinessRules authorBusinessRules, 
        BookMapper bookMapper
    ) {

        this.bookRepository = bookRepository;
        this.bookBusinessRules = bookBusinessRules;
        this.categoryBusinessRules = categoryBusinessRules;
        this.authorBusinessRules = authorBusinessRules;
        this.bookMapper = bookMapper;
    }

    @Override
    public CreateBookResponse handle(CreateBookCommand command) {

        bookBusinessRules.isbnMustBeUnique(command.isbn());

        Category category = categoryBusinessRules.getByIdOrThrow(command.categoryId());

        List<Author> authors = authorBusinessRules.getAllByIdsOrThrow(command.authorIds());

        Book book = bookMapper.bookFromCreateCommand(command);
        book.setCategory(category);
        book.setAuthors(new HashSet<>(authors));

        Book savedBook = bookRepository.save(book);

        return bookMapper.createResponseFromBook(savedBook);
    }

}
