package com.turkcell.library_cqrs_app.application.features.penaltyrecord.rule;

import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.BusinessException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.PenaltyRecord;
import com.turkcell.library_cqrs_app.persistence.repository.PenaltyRecordRepository;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class PenaltyRecordBusinessRules {

    private final PenaltyRecordRepository penaltyRecordRepository;

    public PenaltyRecordBusinessRules(PenaltyRecordRepository penaltyRecordRepository) {
        this.penaltyRecordRepository = penaltyRecordRepository;
    }

    public PenaltyRecord getByIdOrThrow(UUID id) {
        return penaltyRecordRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Ceza kaydı bulunamadı."));
    }

    public void borrowRecordMustNotHavePenalty(UUID borrowRecordId) {
        if (penaltyRecordRepository.existsByBorrowRecordId(borrowRecordId)) {
            throw new AlreadyExistsException("Bu ödünç kaydı için zaten bir ceza oluşturulmuş.");
        }
    }

    public void amountMustBePositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Ceza miktarı sıfırdan büyük olmalıdır.");
        }
    }

    public void penaltyMustBeUnpaid(String paymentStatus) {
        if (paymentStatus.equals("paid")) {
            throw new BusinessException("Bu ceza zaten ödenmiş.");
        }
    }
}
