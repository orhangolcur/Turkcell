package com.turkcell.library_cqrs_app.application.features.reservation.query.getbyid;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Query;
import java.util.UUID;

public record GetByIdReservationQuery(UUID id) implements Query<GetByIdReservationResponse> { }
