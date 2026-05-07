package com.turkcell.library_cqrs_app.application.features.reservation.command.create;

import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate reservationDate,
    String status
) { }
