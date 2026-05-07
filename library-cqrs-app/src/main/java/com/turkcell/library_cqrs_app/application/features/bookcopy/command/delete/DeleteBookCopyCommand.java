package com.turkcell.library_cqrs_app.application.features.bookcopy.command.delete;

import com.turkcell.library_cqrs_app.core.mediator.cqrs.Command;
import java.util.UUID;

public record DeleteBookCopyCommand(UUID id) implements Command<DeleteBookCopyResponse> { }
