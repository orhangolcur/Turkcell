package com.turkcell.library_cqrs_app.application.features.reservation.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.reservation.mapper.ReservationMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdReservationQueryHandler implements QueryHandler<GetByIdReservationQuery, GetByIdReservationResponse> {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public GetByIdReservationQueryHandler(
        ReservationRepository reservationRepository,
        ReservationMapper reservationMapper
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public GetByIdReservationResponse handle(GetByIdReservationQuery query) {
        return reservationRepository.findById(query.id())
            .map(reservationMapper::getByIdResponseFromReservation)
            .orElseThrow(() -> new NotFoundException("Rezervasyon bulunamadı."));
    }
}
