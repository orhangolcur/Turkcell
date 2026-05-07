package com.turkcell.library_cqrs_app.application.features.reservation.rule;

import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.BusinessException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.Reservation;
import com.turkcell.library_cqrs_app.persistence.repository.ReservationRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ReservationBusinessRules {

    private final ReservationRepository reservationRepository;

    public ReservationBusinessRules(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation getByIdOrThrow(UUID id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Rezervasyon bulunamadı."));
    }

    public void studentMustNotHaveActiveReservationForBook(UUID studentId, UUID bookId) {
        if (reservationRepository.existsByStudentIdAndBookIdAndStatus(studentId, bookId, "pending")) {
            throw new AlreadyExistsException("Bu öğrencinin bu kitap için zaten aktif bir rezervasyonu var.");
        }
    }

    public void reservationMustBePending(String status) {
        if (!status.equals("pending")) {
            throw new BusinessException("Sadece beklemedeki rezervasyonlar iptal edilebilir.");
        }
    }
}
