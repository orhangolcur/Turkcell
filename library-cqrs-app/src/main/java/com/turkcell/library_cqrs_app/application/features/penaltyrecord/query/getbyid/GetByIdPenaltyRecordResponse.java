package com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getbyid;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record GetByIdPenaltyRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    BigDecimal amount,
    LocalDate penaltyDate,
    String paymentStatus
) { }
