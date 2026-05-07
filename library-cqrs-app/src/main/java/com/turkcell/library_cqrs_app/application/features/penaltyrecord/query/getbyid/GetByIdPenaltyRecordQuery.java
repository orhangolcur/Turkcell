package com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getbyid;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Query;
import java.util.UUID;

public record GetByIdPenaltyRecordQuery(UUID id) implements Query<GetByIdPenaltyRecordResponse> { }
