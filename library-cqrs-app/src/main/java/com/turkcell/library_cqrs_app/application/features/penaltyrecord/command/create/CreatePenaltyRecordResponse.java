package com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreatePenaltyRecordResponse(
    UUID id,
    String studentName,
    String bookTitle,
    BigDecimal amount,
    LocalDate penaltyDate,
    String paymentStatus
) { }
