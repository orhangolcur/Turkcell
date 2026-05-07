package com.turkcell.library_cqrs_app.application.features.returnrecord.command.create;

import com.turkcell.library_cqrs_app.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.rule.BorrowRecordBusinessRules;
import com.turkcell.library_cqrs_app.application.features.returnrecord.mapper.ReturnRecordMapper;
import com.turkcell.library_cqrs_app.application.features.returnrecord.rule.ReturnRecordBusinessRules;
import com.turkcell.library_cqrs_app.application.features.staff.rule.StaffBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.BorrowRecord;
import com.turkcell.library_cqrs_app.domain.entity.ReturnRecord;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.ReturnRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateReturnRecordCommandHandler implements CommandHandler<CreateReturnRecordCommand, CreateReturnRecordResponse> {

    private final ReturnRecordRepository returnRecordRepository;
    private final ReturnRecordBusinessRules returnRecordBusinessRules;
    private final BorrowRecordBusinessRules borrowRecordBusinessRules;
    private final BookCopyBusinessRules bookCopyBusinessRules;
    private final StaffBusinessRules staffBusinessRules;
    private final ReturnRecordMapper returnRecordMapper;

    public CreateReturnRecordCommandHandler(
        ReturnRecordRepository returnRecordRepository,
        ReturnRecordBusinessRules returnRecordBusinessRules,
        BorrowRecordBusinessRules borrowRecordBusinessRules,
        BookCopyBusinessRules bookCopyBusinessRules,
        StaffBusinessRules staffBusinessRules,                            
        ReturnRecordMapper returnRecordMapper
    ) {
        this.returnRecordRepository = returnRecordRepository;
        this.returnRecordBusinessRules = returnRecordBusinessRules;
        this.borrowRecordBusinessRules = borrowRecordBusinessRules;
        this.bookCopyBusinessRules = bookCopyBusinessRules;
        this.staffBusinessRules = staffBusinessRules;
        this.returnRecordMapper = returnRecordMapper;
    }

    @Override
    public CreateReturnRecordResponse handle(CreateReturnRecordCommand command) {
        returnRecordBusinessRules.borrowRecordMustNotBeReturned(command.borrowRecordId());

        BorrowRecord borrowRecord = borrowRecordBusinessRules.getByIdOrThrow(command.borrowRecordId());
        returnRecordBusinessRules.borrowRecordMustBeActive(borrowRecord.getStatus());

        Staff staff = staffBusinessRules.getByIdOrThrow(command.staffId());

        ReturnRecord returnRecord = returnRecordMapper.returnRecordFromCreateCommand(command);
        returnRecord.setBorrowRecord(borrowRecord);
        returnRecord.setStaff(staff);

        borrowRecordBusinessRules.updateStatus(borrowRecord, "returned", command.returnDate());
        bookCopyBusinessRules.updateStatusByBookId(borrowRecord.getBook().getId(), "available");

        ReturnRecord saved = returnRecordRepository.save(returnRecord);
        return returnRecordMapper.createResponseFromReturnRecord(saved);
    }
}
