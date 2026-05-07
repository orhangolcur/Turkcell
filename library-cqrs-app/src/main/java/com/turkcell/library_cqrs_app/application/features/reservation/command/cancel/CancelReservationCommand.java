package com.turkcell.library_cqrs_app.application.features.reservation.command.cancel;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import java.util.UUID;

public record CancelReservationCommand(UUID id) implements Command<CancelReservationResponse> { }
