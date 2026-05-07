package com.turkcell.library_cqrs_app.application.features.returnrecord.query.getall;

import java.time.LocalDate;
import java.util.UUID;

public record GetAllReturnRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate returnDate,
    String bookCondition,
    String staffName
) { }
