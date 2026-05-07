package com.turkcell.library_cqrs_app.application.features.borrowrecord.mapper;

import com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create.CreateBorrowRecordCommand;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create.CreateBorrowRecordResponse;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getall.GetAllBorrowRecordResponse;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getbyid.GetByIdBorrowRecordResponse;
import com.turkcell.library_cqrs_app.domain.entity.BorrowRecord;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordMapper {

    public BorrowRecord borrowRecordFromCreateCommand(CreateBorrowRecordCommand command) {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBorrowDate(command.borrowDate());
        borrowRecord.setDueDate(command.dueDate());
        borrowRecord.setStatus("active");
        return borrowRecord;
    }

    public CreateBorrowRecordResponse createResponseFromBorrowRecord(BorrowRecord borrowRecord) {
        return new CreateBorrowRecordResponse(
            borrowRecord.getId(),
            borrowRecord.getStudent().getFirstName() + " " + borrowRecord.getStudent().getLastName(),
            borrowRecord.getBook().getTitle(),
            borrowRecord.getBorrowDate(),
            borrowRecord.getDueDate(),
            borrowRecord.getStatus()
        );
    }

    public GetAllBorrowRecordResponse getAllResponseFromBorrowRecord(BorrowRecord borrowRecord) {
        return new GetAllBorrowRecordResponse(
            borrowRecord.getId(),
            borrowRecord.getStudent().getFirstName() + " " + borrowRecord.getStudent().getLastName(),
            borrowRecord.getBook().getTitle(),
            borrowRecord.getBorrowDate(),
            borrowRecord.getDueDate(),
            borrowRecord.getReturnDate(),
            borrowRecord.getStatus()
        );
    }

    public GetByIdBorrowRecordResponse getByIdResponseFromBorrowRecord(BorrowRecord borrowRecord) {
        return new GetByIdBorrowRecordResponse(
            borrowRecord.getId(),
            borrowRecord.getStudent().getFirstName() + " " + borrowRecord.getStudent().getLastName(),
            borrowRecord.getBook().getTitle(),
            borrowRecord.getBorrowDate(),
            borrowRecord.getDueDate(),
            borrowRecord.getReturnDate(),
            borrowRecord.getStatus()
        );
    }
}
