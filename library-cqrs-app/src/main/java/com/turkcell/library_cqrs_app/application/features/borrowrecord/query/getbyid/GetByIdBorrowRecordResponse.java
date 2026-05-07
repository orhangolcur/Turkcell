package com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getbyid;

import java.time.LocalDate;
import java.util.UUID;

public record GetByIdBorrowRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate borrowDate,
    LocalDate dueDate,
    LocalDate returnDate,
    String status
) { }
