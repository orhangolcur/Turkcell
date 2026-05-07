package com.turkcell.library_cqrs_app.application.features.reservation.query.getall;

import com.turkcell.library_cqrs_app.application.features.reservation.mapper.ReservationMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.ReservationRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllReservationQueryHandler implements QueryHandler<GetAllReservationQuery, List<GetAllReservationResponse>> {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public GetAllReservationQueryHandler(
        ReservationRepository reservationRepository,                                 
        ReservationMapper reservationMapper
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<GetAllReservationResponse> handle(GetAllReservationQuery query) {
        return reservationRepository.findAll().stream()
            .map(reservationMapper::getAllResponseFromReservation)
            .toList();
    }
}
