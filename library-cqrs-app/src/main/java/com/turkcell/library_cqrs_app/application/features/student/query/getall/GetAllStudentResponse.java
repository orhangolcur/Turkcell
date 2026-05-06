package com.turkcell.library_cqrs_app.application.features.student.query.getall;

import java.time.LocalDate;
import java.util.UUID;

public record GetAllStudentResponse(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate membershipDate
) { }