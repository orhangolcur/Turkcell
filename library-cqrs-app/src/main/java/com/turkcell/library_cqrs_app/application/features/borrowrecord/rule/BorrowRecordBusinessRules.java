package com.turkcell.library_cqrs_app.application.features.borrowrecord.rule;

import com.turkcell.library_cqrs_app.core.exception.BusinessException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.BorrowRecord;
import com.turkcell.library_cqrs_app.persistence.repository.BorrowRecordRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class BorrowRecordBusinessRules {

    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordBusinessRules(BorrowRecordRepository borrowRecordRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord getByIdOrThrow(UUID id) {
        return borrowRecordRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Ödünç kaydı bulunamadı."));
    }

    public void bookCopyMustBeAvailable(UUID bookCopyId) {
        boolean isBorrowed = borrowRecordRepository.existsByBookIdAndStatus(bookCopyId, "active");
        if (isBorrowed) {
            throw new BusinessException("Bu kitap kopyası zaten ödünç alınmış.");
        }
    }

    public void dueDateMustBeAfterBorrowDate(java.time.LocalDate borrowDate, java.time.LocalDate dueDate) {
        if (!dueDate.isAfter(borrowDate)) {
            throw new BusinessException("İade tarihi, ödünç tarihinden sonra olmalıdır.");
        }
    }

    public void updateStatus(BorrowRecord borrowRecord, String status, LocalDate returnDate) {
        borrowRecord.setStatus(status);
        borrowRecord.setReturnDate(returnDate);
        borrowRecordRepository.save(borrowRecord);
    }
}
