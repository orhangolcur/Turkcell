package com.turkcell.library_cqrs_app.application.features.reservation.command.create;

import com.turkcell.library_cqrs_app.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_cqrs_app.application.features.reservation.mapper.ReservationMapper;
import com.turkcell.library_cqrs_app.application.features.reservation.rule.ReservationBusinessRules;
import com.turkcell.library_cqrs_app.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Book;
import com.turkcell.library_cqrs_app.domain.entity.Reservation;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, CreateReservationResponse> {

    private final ReservationRepository reservationRepository;
    private final ReservationBusinessRules reservationBusinessRules;
    private final StudentBusinessRules studentBusinessRules;
    private final BookBusinessRules bookBusinessRules;
    private final ReservationMapper reservationMapper;

    public CreateReservationCommandHandler(
        ReservationRepository reservationRepository,
        ReservationBusinessRules reservationBusinessRules,
        StudentBusinessRules studentBusinessRules,
        BookBusinessRules bookBusinessRules,    
        ReservationMapper reservationMapper
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationBusinessRules = reservationBusinessRules;
        this.studentBusinessRules = studentBusinessRules;
        this.bookBusinessRules = bookBusinessRules;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public CreateReservationResponse handle(CreateReservationCommand command) {
        reservationBusinessRules.studentMustNotHaveActiveReservationForBook(command.studentId(), command.bookId());

        Student student = studentBusinessRules.getByIdOrThrow(command.studentId());
        Book book = bookBusinessRules.getByIdOrThrow(command.bookId());

        Reservation reservation = reservationMapper.reservationFromCreateCommand(command);
        reservation.setStudent(student);
        reservation.setBook(book);

        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.createResponseFromReservation(saved);
    }
}
