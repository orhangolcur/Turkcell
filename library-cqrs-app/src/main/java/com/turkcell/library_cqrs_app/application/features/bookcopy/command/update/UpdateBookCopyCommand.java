package com.turkcell.library_cqrs_app.application.features.bookcopy.command.update;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UpdateBookCopyCommand(
    UUID id,

    @NotBlank(message = "Barkod boş olamaz")
    @Size(max = 50, message = "Barkod en fazla 50 karakter olabilir")
    String barcode,

    @NotBlank(message = "Durum boş olamaz")
    String status,

    @NotNull(message = "Kitap boş olamaz")
    UUID bookId,

    @NotNull(message = "Şube boş olamaz")
    UUID branchId
) implements Command<UpdateBookCopyResponse> { }
