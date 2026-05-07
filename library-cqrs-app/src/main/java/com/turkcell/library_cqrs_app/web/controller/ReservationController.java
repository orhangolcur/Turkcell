package com.turkcell.library_cqrs_app.web.controller;

import com.turkcell.library_cqrs_app.application.features.reservation.command.cancel.CancelReservationCommand;
import com.turkcell.library_cqrs_app.application.features.reservation.command.cancel.CancelReservationResponse;
import com.turkcell.library_cqrs_app.application.features.reservation.command.create.CreateReservationCommand;
import com.turkcell.library_cqrs_app.application.features.reservation.command.create.CreateReservationResponse;
import com.turkcell.library_cqrs_app.application.features.reservation.query.getall.GetAllReservationQuery;
import com.turkcell.library_cqrs_app.application.features.reservation.query.getall.GetAllReservationResponse;
import com.turkcell.library_cqrs_app.application.features.reservation.query.getbyid.GetByIdReservationQuery;
import com.turkcell.library_cqrs_app.application.features.reservation.query.getbyid.GetByIdReservationResponse;
import com.turkcell.library_cqrs_app.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final Mediator mediator;

    public ReservationController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateReservationResponse create(@RequestBody @Valid CreateReservationCommand command) {
        return mediator.send(command);
    }

    @PatchMapping("/{id}/cancel")
    public CancelReservationResponse cancel(@PathVariable UUID id) {
        return mediator.send(new CancelReservationCommand(id));
    }

    @GetMapping
    public List<GetAllReservationResponse> getAll() {
        return mediator.send(new GetAllReservationQuery());
    }

    @GetMapping("/{id}")
    public GetByIdReservationResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetByIdReservationQuery(id));
    }
}
