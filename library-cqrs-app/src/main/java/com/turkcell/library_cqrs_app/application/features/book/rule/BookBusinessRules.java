package com.turkcell.library_cqrs_app.application.features.book.rule;

import java.util.UUID;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.Book;
import com.turkcell.library_cqrs_app.persistence.repository.BookRepository;

@Component
public class BookBusinessRules {

    private final BookRepository bookRepository;

    public BookBusinessRules(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void isbnMustBeUnique(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new AlreadyExistsException("Bu ISBN zaten kayıtlı");
        }
    }

    public void isbnMustBeUniqueForUpdate(UUID id, String isbn) {
        bookRepository.findById(id).ifPresent(book -> {
            if (!book.getIsbn().equals(isbn) && bookRepository.existsByIsbn(isbn)) {
                throw new AlreadyExistsException("Bu ISBN zaten kayıtlı");
            }
        });
    }

    public void bookMustExist(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Kitap bulunamadı");
        }
    }

    public Book getByIdOrThrow(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kitap bulunamadı"));
    }
}
