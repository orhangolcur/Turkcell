package com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.pay;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import java.util.UUID;

public record PayPenaltyCommand(UUID id) implements Command<PayPenaltyResponse> { }
