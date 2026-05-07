package com.turkcell.library_cqrs_app.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.turkcell.library_cqrs_app.domain.entity.BorrowRecord;
import java.util.UUID;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, UUID> {
    boolean existsByBookIdAndStatus(UUID bookId, String status);

}