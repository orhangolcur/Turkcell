package com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreatePenaltyRecordCommand(
    @NotNull(message = "Öğrenci boş olamaz")
    UUID studentId,

    @NotNull(message = "Ödünç kaydı boş olamaz")
    UUID borrowRecordId,

    @NotNull(message = "Ceza miktarı boş olamaz")
    @Positive(message = "Ceza miktarı sıfırdan büyük olmalıdır")
    BigDecimal amount,

    @NotNull(message = "Ceza tarihi boş olamaz")
    LocalDate penaltyDate
) implements Command<CreatePenaltyRecordResponse> { }
