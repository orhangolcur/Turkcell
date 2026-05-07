package com.turkcell.library_cqrs_app.application.features.returnrecord.query.getbyid;

import java.time.LocalDate;
import java.util.UUID;

public record GetByIdReturnRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate returnDate,
    String bookCondition,
    String staffName
) { }
