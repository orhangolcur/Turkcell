package com.turkcell.library_cqrs_app.application.features.bookcopy.command.delete;

import java.util.UUID;

public record DeleteBookCopyResponse(
    UUID id,
    String message
) { }
