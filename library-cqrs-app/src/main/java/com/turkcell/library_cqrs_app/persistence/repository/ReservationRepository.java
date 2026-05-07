package com.turkcell.library_cqrs_app.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.turkcell.library_cqrs_app.domain.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    boolean existsByStudentIdAndBookIdAndStatus(UUID studentId, UUID bookId, String status);
}
