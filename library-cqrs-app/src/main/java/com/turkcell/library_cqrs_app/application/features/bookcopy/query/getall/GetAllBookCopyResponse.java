package com.turkcell.library_cqrs_app.application.features.bookcopy.query.getall;

import java.util.UUID;

public record GetAllBookCopyResponse(
    UUID id,
    String barcode,
    String status,
    String bookTitle,
    String branchName
) { }
