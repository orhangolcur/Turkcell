package com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.pay;

import java.util.UUID;

public record PayPenaltyResponse(
    UUID id,
    String message
) { }
