package com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getbyid;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Query;
import java.util.UUID;

public record GetByIdBorrowRecordQuery(UUID id) implements Query<GetByIdBorrowRecordResponse> { }
