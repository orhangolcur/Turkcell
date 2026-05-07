package com.turkcell.library_cqrs_app.application.features.returnrecord.mapper;

import com.turkcell.library_cqrs_app.application.features.returnrecord.command.create.CreateReturnRecordCommand;
import com.turkcell.library_cqrs_app.application.features.returnrecord.command.create.CreateReturnRecordResponse;
import com.turkcell.library_cqrs_app.application.features.returnrecord.query.getall.GetAllReturnRecordResponse;
import com.turkcell.library_cqrs_app.application.features.returnrecord.query.getbyid.GetByIdReturnRecordResponse;
import com.turkcell.library_cqrs_app.domain.entity.ReturnRecord;
import org.springframework.stereotype.Component;

@Component
public class ReturnRecordMapper {

    public ReturnRecord returnRecordFromCreateCommand(CreateReturnRecordCommand command) {
        ReturnRecord returnRecord = new ReturnRecord();
        returnRecord.setReturnDate(command.returnDate());
        returnRecord.setBookCondition(command.bookCondition());
        return returnRecord;
    }

    public CreateReturnRecordResponse createResponseFromReturnRecord(ReturnRecord returnRecord) {
        return new CreateReturnRecordResponse(
            returnRecord.getId(),
            returnRecord.getBorrowRecord().getStudent().getFirstName() + " " + returnRecord.getBorrowRecord().getStudent().getLastName(),
            returnRecord.getBorrowRecord().getBook().getTitle(),
            returnRecord.getReturnDate(),
            returnRecord.getBookCondition(),
            returnRecord.getStaff().getFirstName() + " " + returnRecord.getStaff().getLastName()
        );
    }

    public GetAllReturnRecordResponse getAllResponseFromReturnRecord(ReturnRecord returnRecord) {
        return new GetAllReturnRecordResponse(
            returnRecord.getId(),
            returnRecord.getBorrowRecord().getStudent().getFirstName() + " " + returnRecord.getBorrowRecord().getStudent().getLastName(),
            returnRecord.getBorrowRecord().getBook().getTitle(),
            returnRecord.getReturnDate(),
            returnRecord.getBookCondition(),
            returnRecord.getStaff().getFirstName() + " " + returnRecord.getStaff().getLastName()
        );
    }

    public GetByIdReturnRecordResponse getByIdResponseFromReturnRecord(ReturnRecord returnRecord) {
        return new GetByIdReturnRecordResponse(
            returnRecord.getId(),
            returnRecord.getBorrowRecord().getStudent().getFirstName() + " " + returnRecord.getBorrowRecord().getStudent().getLastName(),
            returnRecord.getBorrowRecord().getBook().getTitle(),
            returnRecord.getReturnDate(),
            returnRecord.getBookCondition(),
            returnRecord.getStaff().getFirstName() + " " + returnRecord.getStaff().getLastName()
        );
    }
}
