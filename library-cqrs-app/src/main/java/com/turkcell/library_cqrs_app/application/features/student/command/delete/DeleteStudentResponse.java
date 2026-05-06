package com.turkcell.library_cqrs_app.application.features.student.command.delete;

import java.util.UUID;

public record DeleteStudentResponse(
    UUID id,
    String message
) { }