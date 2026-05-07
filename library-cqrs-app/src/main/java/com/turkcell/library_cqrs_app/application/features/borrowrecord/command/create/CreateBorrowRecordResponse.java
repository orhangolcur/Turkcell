package com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create;

import java.time.LocalDate;
import java.util.UUID;

public record CreateBorrowRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate borrowDate,
    LocalDate dueDate,
    String status
) { }
