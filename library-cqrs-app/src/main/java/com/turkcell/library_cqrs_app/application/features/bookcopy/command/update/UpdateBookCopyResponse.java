package com.turkcell.library_cqrs_app.application.features.bookcopy.command.update;

import java.util.UUID;

public record UpdateBookCopyResponse(
    UUID id,
    String barcode,
    String status,
    String bookTitle,
    String branchName
) { }
