package com.turkcell.library_cqrs_app.application.features.reservation.command.cancel;

import com.turkcell.library_cqrs_app.application.features.reservation.rule.ReservationBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Reservation;
import com.turkcell.library_cqrs_app.persistence.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class CancelReservationCommandHandler implements CommandHandler<CancelReservationCommand, CancelReservationResponse> {

    private final ReservationRepository reservationRepository;
    private final ReservationBusinessRules reservationBusinessRules;

    public CancelReservationCommandHandler(
        ReservationRepository reservationRepository,    
        ReservationBusinessRules reservationBusinessRules
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationBusinessRules = reservationBusinessRules;
    }

    @Override
    public CancelReservationResponse handle(CancelReservationCommand command) {
        Reservation reservation = reservationBusinessRules.getByIdOrThrow(command.id());
        reservationBusinessRules.reservationMustBePending(reservation.getStatus());

        reservation.setStatus("cancelled");
        reservationRepository.save(reservation);

        return new CancelReservationResponse(
            command.id(),
            "Rezervasyon başarıyla iptal edildi."
        );
    }
}
