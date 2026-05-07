package com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create;

import com.turkcell.library_cqrs_app.application.features.borrowrecord.rule.BorrowRecordBusinessRules;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.mapper.PenaltyRecordMapper;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.rule.PenaltyRecordBusinessRules;
import com.turkcell.library_cqrs_app.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.BorrowRecord;
import com.turkcell.library_cqrs_app.domain.entity.PenaltyRecord;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.PenaltyRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePenaltyRecordCommandHandler implements CommandHandler<CreatePenaltyRecordCommand, CreatePenaltyRecordResponse> {

    private final PenaltyRecordRepository penaltyRecordRepository;
    private final PenaltyRecordBusinessRules penaltyRecordBusinessRules;
    private final StudentBusinessRules studentBusinessRules;
    private final BorrowRecordBusinessRules borrowRecordBusinessRules;
    private final PenaltyRecordMapper penaltyRecordMapper;

    public CreatePenaltyRecordCommandHandler(
        PenaltyRecordRepository penaltyRecordRepository,
        PenaltyRecordBusinessRules penaltyRecordBusinessRules,
        StudentBusinessRules studentBusinessRules,
        BorrowRecordBusinessRules borrowRecordBusinessRules,
        PenaltyRecordMapper penaltyRecordMapper
    ) {
        this.penaltyRecordRepository = penaltyRecordRepository;
        this.penaltyRecordBusinessRules = penaltyRecordBusinessRules;
        this.studentBusinessRules = studentBusinessRules;
        this.borrowRecordBusinessRules = borrowRecordBusinessRules;
        this.penaltyRecordMapper = penaltyRecordMapper;
    }

    @Override
    public CreatePenaltyRecordResponse handle(CreatePenaltyRecordCommand command) {
        penaltyRecordBusinessRules.borrowRecordMustNotHavePenalty(command.borrowRecordId());
        penaltyRecordBusinessRules.amountMustBePositive(command.amount());

        Student student = studentBusinessRules.getByIdOrThrow(command.studentId());
        BorrowRecord borrowRecord = borrowRecordBusinessRules.getByIdOrThrow(command.borrowRecordId());

        PenaltyRecord penaltyRecord = penaltyRecordMapper.penaltyRecordFromCreateCommand(command);
        penaltyRecord.setStudent(student);
        penaltyRecord.setBorrowRecord(borrowRecord);

        PenaltyRecord saved = penaltyRecordRepository.save(penaltyRecord);
        return penaltyRecordMapper.createResponseFromPenaltyRecord(saved);
    }
}
