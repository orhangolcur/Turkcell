package com.turkcell.library_cqrs_app.application.features.bookcopy.command.create;

import java.util.UUID;

public record CreateBookCopyResponse(
    UUID id,
    String barcode,
    String status,
    String bookTitle,
    String branchName
) { }
