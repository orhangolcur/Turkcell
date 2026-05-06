package com.turkcell.library_cqrs_app.application.features.book.mapper;

import com.turkcell.library_cqrs_app.application.features.book.command.create.CreateBookCommand;
import com.turkcell.library_cqrs_app.application.features.book.command.create.CreateBookResponse;
import com.turkcell.library_cqrs_app.application.features.book.command.update.UpdateBookCommand;
import com.turkcell.library_cqrs_app.application.features.book.command.update.UpdateBookResponse;
import com.turkcell.library_cqrs_app.application.features.book.query.getall.GetAllBookResponse;
import com.turkcell.library_cqrs_app.application.features.book.query.getbyid.GetByIdBookResponse;
import com.turkcell.library_cqrs_app.domain.entity.Book;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BookMapper {

    public Book bookFromCreateCommand(CreateBookCommand command) {
        Book book = new Book();
        book.setIsbn(command.isbn());
        book.setTitle(command.title());
        book.setStock(command.stock());
        book.setPublishYear(command.publishYear());
        return book;
    }

    public Book bookFromUpdateCommand(Book book, UpdateBookCommand command) {
        book.setIsbn(command.isbn());
        book.setTitle(command.title());
        book.setStock(command.stock());
        book.setPublishYear(command.publishYear());
        return book;
    }

    public CreateBookResponse createResponseFromBook(Book book) {
        return new CreateBookResponse(
            book.getId(),
            book.getIsbn(),
            book.getTitle(),
            book.getStock(),
            book.getPublishYear(),
            book.getCategory() != null ? book.getCategory().getName() : null,
            book.getAuthors() != null
                ? book.getAuthors().stream()
                      .map(a -> a.getFirstName() + " " + a.getLastName())
                      .toList()
                : List.of()
        );
    }

    public UpdateBookResponse updateResponseFromBook(Book book) {
        return new UpdateBookResponse(
            book.getId(),
            book.getIsbn(),
            book.getTitle(),
            book.getStock(),
            book.getPublishYear(),
            book.getCategory() != null ? book.getCategory().getName() : null,
            book.getAuthors() != null
                ? book.getAuthors().stream()
                      .map(a -> a.getFirstName() + " " + a.getLastName())
                      .toList()
                : List.of()
        );
    }

    public GetAllBookResponse getAllResponseFromBook(Book book) {
        return new GetAllBookResponse(
            book.getId(),
            book.getIsbn(),
            book.getTitle(),
            book.getStock(),
            book.getPublishYear(),
            book.getCategory() != null ? book.getCategory().getName() : null,
            book.getAuthors() != null
                ? book.getAuthors().stream()
                      .map(a -> a.getFirstName() + " " + a.getLastName())
                      .toList()
                : List.of()
        );
    }

    public GetByIdBookResponse getByIdResponseFromBook(Book book) {
        return new GetByIdBookResponse(
            book.getId(),
            book.getIsbn(),
            book.getTitle(),
            book.getStock(),
            book.getPublishYear(),
            book.getCategory() != null ? book.getCategory().getName() : null,
            book.getAuthors() != null
                ? book.getAuthors().stream()
                      .map(a -> a.getFirstName() + " " + a.getLastName())
                      .toList()
                : List.of()
        );
    }
}