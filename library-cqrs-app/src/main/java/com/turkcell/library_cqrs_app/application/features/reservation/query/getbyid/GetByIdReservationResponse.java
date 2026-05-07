package com.turkcell.library_cqrs_app.application.features.reservation.query.getbyid;

import java.time.LocalDate;
import java.util.UUID;

public record GetByIdReservationResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate reservationDate,
    String status
) { }
