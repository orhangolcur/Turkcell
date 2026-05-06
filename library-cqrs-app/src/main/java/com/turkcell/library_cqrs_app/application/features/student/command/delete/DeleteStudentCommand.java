package com.turkcell.library_cqrs_app.application.features.student.command.delete;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import java.util.UUID;

public record DeleteStudentCommand(UUID id) implements Command<DeleteStudentResponse> { }