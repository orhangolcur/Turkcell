package com.turkcell.library_cqrs_app.application.features.student.command.update;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateStudentResponse(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate membershipDate
) { }