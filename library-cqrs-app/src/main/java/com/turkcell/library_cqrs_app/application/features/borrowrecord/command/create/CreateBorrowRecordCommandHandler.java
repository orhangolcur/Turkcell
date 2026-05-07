package com.turkcell.library_cqrs_app.application.features.borrowrecord.command.create;

import com.turkcell.library_cqrs_app.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.mapper.BorrowRecordMapper;
import com.turkcell.library_cqrs_app.application.features.borrowrecord.rule.BorrowRecordBusinessRules;
import com.turkcell.library_cqrs_app.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.BookCopy;
import com.turkcell.library_cqrs_app.domain.entity.BorrowRecord;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.BorrowRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateBorrowRecordCommandHandler implements CommandHandler<CreateBorrowRecordCommand, CreateBorrowRecordResponse> {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordBusinessRules borrowRecordBusinessRules;
    private final StudentBusinessRules studentBusinessRules;
    private final BookCopyBusinessRules bookCopyBusinessRules;
    private final BorrowRecordMapper borrowRecordMapper;

    public CreateBorrowRecordCommandHandler(
        BorrowRecordRepository borrowRecordRepository,
        BorrowRecordBusinessRules borrowRecordBusinessRules,
        StudentBusinessRules studentBusinessRules,
        BookCopyBusinessRules bookCopyBusinessRules,
        BorrowRecordMapper borrowRecordMapper
    ) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.borrowRecordBusinessRules = borrowRecordBusinessRules;
        this.studentBusinessRules = studentBusinessRules;
        this.bookCopyBusinessRules = bookCopyBusinessRules;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @Override
    public CreateBorrowRecordResponse handle(CreateBorrowRecordCommand command) {
        borrowRecordBusinessRules.dueDateMustBeAfterBorrowDate(command.borrowDate(), command.dueDate());
        borrowRecordBusinessRules.bookCopyMustBeAvailable(command.bookCopyId());

        Student student = studentBusinessRules.getByIdOrThrow(command.studentId());
        BookCopy bookCopy = bookCopyBusinessRules.getByIdOrThrow(command.bookCopyId());

        BorrowRecord borrowRecord = borrowRecordMapper.borrowRecordFromCreateCommand(command);
        borrowRecord.setStudent(student);
        borrowRecord.setBook(bookCopy.getBook());

        bookCopyBusinessRules.updateStatus(bookCopy, "borrowed");

        BorrowRecord saved = borrowRecordRepository.save(borrowRecord);
        return borrowRecordMapper.createResponseFromBorrowRecord(saved);
    }
}