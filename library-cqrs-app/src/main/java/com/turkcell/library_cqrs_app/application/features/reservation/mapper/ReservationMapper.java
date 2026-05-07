package com.turkcell.library_cqrs_app.application.features.reservation.mapper;

import com.turkcell.library_cqrs_app.application.features.reservation.command.create.CreateReservationCommand;
import com.turkcell.library_cqrs_app.application.features.reservation.command.create.CreateReservationResponse;
import com.turkcell.library_cqrs_app.application.features.reservation.query.getall.GetAllReservationResponse;
import com.turkcell.library_cqrs_app.application.features.reservation.query.getbyid.GetByIdReservationResponse;
import com.turkcell.library_cqrs_app.domain.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation reservationFromCreateCommand(CreateReservationCommand command) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(command.reservationDate());
        reservation.setStatus("pending");
        return reservation;
    }

    public CreateReservationResponse createResponseFromReservation(Reservation reservation) {
        return new CreateReservationResponse(
            reservation.getId(),
            reservation.getStudent().getFirstName() + " " + reservation.getStudent().getLastName(),
            reservation.getBook().getTitle(),
            reservation.getReservationDate(),
            reservation.getStatus()
        );
    }

    public GetAllReservationResponse getAllResponseFromReservation(Reservation reservation) {
        return new GetAllReservationResponse(
            reservation.getId(),
            reservation.getStudent().getFirstName() + " " + reservation.getStudent().getLastName(),
            reservation.getBook().getTitle(),
            reservation.getReservationDate(),
            reservation.getStatus()
        );
    }

    public GetByIdReservationResponse getByIdResponseFromReservation(Reservation reservation) {
        return new GetByIdReservationResponse(
            reservation.getId(),
            reservation.getStudent().getFirstName() + " " + reservation.getStudent().getLastName(),
            reservation.getBook().getTitle(),
            reservation.getReservationDate(),
            reservation.getStatus()
        );
    }
}
