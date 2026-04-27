package com.turkcell.repository;

import com.turkcell.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, UUID> {
}