package com.turkcell.library_cqrs_app.application.features.bookcopy.query.getbyid;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Query;
import java.util.UUID;

public record GetByIdBookCopyQuery(UUID id) implements Query<GetByIdBookCopyResponse> { }
