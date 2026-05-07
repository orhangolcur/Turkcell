package com.turkcell.library_cqrs_app.application.features.bookcopy.query.getbyid;

import java.util.UUID;

public record GetByIdBookCopyResponse(
    UUID id,
    String barcode,
    String status,
    String bookTitle,
    String branchName
) { }
