package com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateBorrowRecordCommand(
    @NotNull(message = "Öğrenci boş olamaz")
    UUID studentId,

    @NotNull(message = "Kitap kopyası boş olamaz")
    UUID bookCopyId,

    @NotNull(message = "Ödünç tarihi boş olamaz")
    LocalDate borrowDate,

    @NotNull(message = "İade tarihi boş olamaz")
    LocalDate dueDate
) implements Command<CreateBorrowRecordResponse> { }
