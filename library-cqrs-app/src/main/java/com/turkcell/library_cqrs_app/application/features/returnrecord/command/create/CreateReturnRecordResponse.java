package com.turkcell.library_cqrs_app.application.features.returnrecord.command.create;

import java.time.LocalDate;
import java.util.UUID;

public record CreateReturnRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate returnDate,
    String bookCondition,
    String staffName
) { }
