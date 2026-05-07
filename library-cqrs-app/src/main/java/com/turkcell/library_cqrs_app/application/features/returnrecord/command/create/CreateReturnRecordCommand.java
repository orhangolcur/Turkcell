package com.turkcell.library_cqrs_app.application.features.returnrecord.command.create;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReturnRecordCommand(
    @NotNull(message = "Ödünç kaydı boş olamaz")
    UUID borrowRecordId,

    @NotNull(message = "Görevli boş olamaz")
    UUID staffId,

    @NotNull(message = "İade tarihi boş olamaz")
    LocalDate returnDate,

    @NotBlank(message = "Kitap durumu boş olamaz")
    String bookCondition
) implements Command<CreateReturnRecordResponse> { }
