package com.turkcell.library_cqrs_app.application.features.student.query.getbyid;

import java.time.LocalDate;
import java.util.UUID;

public record GetByIdStudentResponse(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate membershipDate
) { }