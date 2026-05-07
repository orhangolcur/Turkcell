package com.turkcell.library_cqrs_app.web.controller;

import com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create.CreatePenaltyRecordCommand;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create.CreatePenaltyRecordResponse;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.pay.PayPenaltyCommand;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.pay.PayPenaltyResponse;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getall.GetAllPenaltyRecordQuery;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getall.GetAllPenaltyRecordResponse;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getbyid.GetByIdPenaltyRecordQuery;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getbyid.GetByIdPenaltyRecordResponse;
import com.turkcell.library_cqrs_app.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/penalty-records")
public class PenaltyRecordController {

    private final Mediator mediator;

    public PenaltyRecordController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreatePenaltyRecordResponse create(@RequestBody @Valid CreatePenaltyRecordCommand command) {
        return mediator.send(command);
    }

    @PatchMapping("/{id}/pay")
    public PayPenaltyResponse pay(@PathVariable UUID id) {
        return mediator.send(new PayPenaltyCommand(id));
    }

    @GetMapping
    public List<GetAllPenaltyRecordResponse> getAll() {
        return mediator.send(new GetAllPenaltyRecordQuery());
    }

    @GetMapping("/{id}")
    public GetByIdPenaltyRecordResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetByIdPenaltyRecordQuery(id));
    }
}
