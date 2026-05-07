package com.turkcell.library_cqrs_app.application.features.returnrecord.rule;

import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.BusinessException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.ReturnRecord;
import com.turkcell.library_cqrs_app.persistence.repository.ReturnRecordRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ReturnRecordBusinessRules {

    private final ReturnRecordRepository returnRecordRepository;

    public ReturnRecordBusinessRules(ReturnRecordRepository returnRecordRepository) {
        this.returnRecordRepository = returnRecordRepository;
    }

    public ReturnRecord getByIdOrThrow(UUID id) {
        return returnRecordRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("İade kaydı bulunamadı."));
    }

    public void borrowRecordMustNotBeReturned(UUID borrowRecordId) {
        if (returnRecordRepository.existsByBorrowRecordId(borrowRecordId)) {
            throw new AlreadyExistsException("Bu ödünç kaydı zaten iade edilmiş.");
        }
    }

    public void borrowRecordMustBeActive(String status) {
        if (!status.equals("active")) {
            throw new BusinessException("Sadece aktif ödünç kayıtları iade edilebilir.");
        }
    }
}
