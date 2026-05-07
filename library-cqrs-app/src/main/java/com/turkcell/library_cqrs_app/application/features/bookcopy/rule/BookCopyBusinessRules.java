package com.turkcell.library_cqrs_app.application.features.bookcopy.rule;

import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;
import com.turkcell.library_cqrs_app.persistence.repository.BookCopyRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class BookCopyBusinessRules {

    private final BookCopyRepository bookCopyRepository;

    public BookCopyBusinessRules(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public BookCopy getByIdOrThrow(UUID id) {
        return bookCopyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Kitap kopyası bulunamadı."));
    }

    public void barcodeMustBeUnique(String barcode) {
        if (bookCopyRepository.existsByBarcode(barcode)) {
            throw new AlreadyExistsException("Bu barkod zaten kayıtlı");
        }
    }

    public void barcodeMustBeUniqueForUpdate(UUID id, String barcode) {
        bookCopyRepository.findById(id).ifPresent(bookCopy -> {
            if (!bookCopy.getBarcode().equals(barcode) && bookCopyRepository.existsByBarcode(barcode)) {
                throw new AlreadyExistsException("Bu barkod zaten kayıtlı");
            }
        });
    }

    public void updateStatusByBookId(UUID bookId, String status) {
        bookCopyRepository.findByBookId(bookId).ifPresent(bookCopy -> {
            bookCopy.setStatus(status);
            bookCopyRepository.save(bookCopy);
        });
    }

    public void updateStatus(BookCopy bookCopy, String status) {
        bookCopy.setStatus(status);
        bookCopyRepository.save(bookCopy);
    }
}
