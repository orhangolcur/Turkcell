package com.turkcell.library_cqrs_app.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.turkcell.library_cqrs_app.domain.entity.ReturnRecord;

@Repository
public interface ReturnRecordRepository extends JpaRepository<ReturnRecord, UUID> {
    boolean existsByBorrowRecordId(UUID borrowRecordId);
}
