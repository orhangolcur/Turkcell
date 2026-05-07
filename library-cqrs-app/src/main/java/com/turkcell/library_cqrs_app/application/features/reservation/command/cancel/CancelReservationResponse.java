package com.turkcell.library_cqrs_app.application.features.reservation.command.cancel;

import java.util.UUID;

public record CancelReservationResponse(
    UUID id,
    String message
) { }
