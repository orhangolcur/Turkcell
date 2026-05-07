package com.turkcell.library_cqrs_app.application.features.bookcopy.command.create;

import java.util.UUID;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBookCopyCommand(
    @NotBlank(message = "Barkod boş olamaz")
    @Size(max = 50, message = "Barkod en fazla 50 karakter olabilir")
    String barcode,

    @NotBlank(message = "Durum boş olamaz")
    String status,

    @NotNull(message = "Kitap boş olamaz")
    UUID bookId,

    @NotNull(message = "Şube boş olamaz")
    UUID branchId
) implements Command<CreateBookCopyResponse> { }
