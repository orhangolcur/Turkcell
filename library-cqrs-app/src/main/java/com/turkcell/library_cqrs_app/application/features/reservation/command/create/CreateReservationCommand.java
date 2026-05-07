package com.turkcell.library_cqrs_app.application.features.reservation.command.create;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationCommand(
    @NotNull(message = "Öğrenci boş olamaz")
    UUID studentId,

    @NotNull(message = "Kitap boş olamaz")
    UUID bookId,

    @NotNull(message = "Rezervasyon tarihi boş olamaz")
    LocalDate reservationDate
) implements Command<CreateReservationResponse> { }
