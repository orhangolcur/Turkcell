package com.turkcell.library_cqrs_app.application.features.student.command.create;

import java.time.LocalDate;
import java.util.UUID;

public record CreateStudentResponse(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate membershipDate
) { }