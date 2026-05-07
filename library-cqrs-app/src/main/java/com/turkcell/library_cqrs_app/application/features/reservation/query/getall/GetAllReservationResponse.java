package com.turkcell.library_cqrs_app.application.features.reservation.query.getall;

import java.time.LocalDate;
import java.util.UUID;

public record GetAllReservationResponse(
    UUID id,
    String studentName,
    String bookTitle,
    LocalDate reservationDate,
    String status
) { }
