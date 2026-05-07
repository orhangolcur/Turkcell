package com.turkcell.library_cqrs_app.web.controller;

import com.turkcell.library_cqrs_app.application.features.returnrecord.command.create.CreateReturnRecordCommand;
import com.turkcell.library_cqrs_app.application.features.returnrecord.command.create.CreateReturnRecordResponse;
import com.turkcell.library_cqrs_app.application.features.returnrecord.query.getall.GetAllReturnRecordQuery;
import com.turkcell.library_cqrs_app.application.features.returnrecord.query.getall.GetAllReturnRecordResponse;
import com.turkcell.library_cqrs_app.application.features.returnrecord.query.getbyid.GetByIdReturnRecordQuery;
import com.turkcell.library_cqrs_app.application.features.returnrecord.query.getbyid.GetByIdReturnRecordResponse;
import com.turkcell.library_cqrs_app.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/return-records")
public class ReturnRecordController {

    private final Mediator mediator;

    public ReturnRecordController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateReturnRecordResponse create(@RequestBody @Valid CreateReturnRecordCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<GetAllReturnRecordResponse> getAll() {
        return mediator.send(new GetAllReturnRecordQuery());
    }

    @GetMapping("/{id}")
    public GetByIdReturnRecordResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetByIdReturnRecordQuery(id));
    }
}
