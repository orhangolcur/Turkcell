package com.turkcell.library_cqrs_app.web.controller;

import com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create.CreateBorrowRecordCommand;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create.CreateBorrowRecordResponse;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getall.GetAllBorrowRecordQuery;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getall.GetAllBorrowRecordResponse;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getbyid.GetByIdBorrowRecordQuery;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getbyid.GetByIdBorrowRecordResponse;
import com.turkcell.library_cqrs_app.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    private final Mediator mediator;

    public BorrowRecordController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateBorrowRecordResponse create(@RequestBody @Valid CreateBorrowRecordCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<GetAllBorrowRecordResponse> getAll() {
        return mediator.send(new GetAllBorrowRecordQuery());
    }

    @GetMapping("/{id}")
    public GetByIdBorrowRecordResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetByIdBorrowRecordQuery(id));
    }
}
