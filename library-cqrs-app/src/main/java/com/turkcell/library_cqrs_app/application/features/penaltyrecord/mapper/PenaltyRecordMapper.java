package com.turkcell.library_cqrs_app.application.features.penaltyrecord.mapper;

import com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create.CreatePenaltyRecordCommand;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.command.create.CreatePenaltyRecordResponse;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getall.GetAllPenaltyRecordResponse;
import com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getbyid.GetByIdPenaltyRecordResponse;
import com.turkcell.library_cqrs_app.domain.entity.PenaltyRecord;
import org.springframework.stereotype.Component;

@Component
public class PenaltyRecordMapper {

    public PenaltyRecord penaltyRecordFromCreateCommand(CreatePenaltyRecordCommand command) {
        PenaltyRecord penaltyRecord = new PenaltyRecord();
        penaltyRecord.setAmount(command.amount());
        penaltyRecord.setPenaltyDate(command.penaltyDate());
        penaltyRecord.setPaymentStatus("unpaid");
        return penaltyRecord;
    }

    public CreatePenaltyRecordResponse createResponseFromPenaltyRecord(PenaltyRecord penaltyRecord) {
        return new CreatePenaltyRecordResponse(
            penaltyRecord.getId(),
            penaltyRecord.getStudent().getFirstName() + " " + penaltyRecord.getStudent().getLastName(),
            penaltyRecord.getBorrowRecord().getBook().getTitle(),
            penaltyRecord.getAmount(),
            penaltyRecord.getPenaltyDate(),
            penaltyRecord.getPaymentStatus()
        );
    }

    public GetAllPenaltyRecordResponse getAllResponseFromPenaltyRecord(PenaltyRecord penaltyRecord) {
        return new GetAllPenaltyRecordResponse(
            penaltyRecord.getId(),
            penaltyRecord.getStudent().getFirstName() + " " + penaltyRecord.getStudent().getLastName(),
            penaltyRecord.getBorrowRecord().getBook().getTitle(),
            penaltyRecord.getAmount(),
            penaltyRecord.getPenaltyDate(),
            penaltyRecord.getPaymentStatus()
        );
    }

    public GetByIdPenaltyRecordResponse getByIdResponseFromPenaltyRecord(PenaltyRecord penaltyRecord) {
        return new GetByIdPenaltyRecordResponse(
            penaltyRecord.getId(),
            penaltyRecord.getStudent().getFirstName() + " " + penaltyRecord.getStudent().getLastName(),
            penaltyRecord.getBorrowRecord().getBook().getTitle(),
            penaltyRecord.getAmount(),
            penaltyRecord.getPenaltyDate(),
            penaltyRecord.getPaymentStatus()
        );
    }
}
