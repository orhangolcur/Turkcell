package com.turkcell.library_cqrs_app.application.features.returnrecord.query.getbyid;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Query;
import java.util.UUID;

public record GetByIdReturnRecordQuery(UUID id) implements Query<GetByIdReturnRecordResponse> { }
