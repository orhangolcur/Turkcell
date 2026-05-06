package com.turkcell.library_cqrs_app.application.features.student.query.getbyid;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Query;
import java.util.UUID;

public record GetByIdStudentQuery(UUID id) implements Query<GetByIdStudentResponse> { }