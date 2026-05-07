package com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.borrowrecord.mapper.BorrowRecordMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BorrowRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdBorrowRecordQueryHandler implements QueryHandler<GetByIdBorrowRecordQuery, GetByIdBorrowRecordResponse> {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    public GetByIdBorrowRecordQueryHandler(
        BorrowRecordRepository borrowRecordRepository,                                   
        BorrowRecordMapper borrowRecordMapper
    ) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @Override
    public GetByIdBorrowRecordResponse handle(GetByIdBorrowRecordQuery query) {
        return borrowRecordRepository.findById(query.id())
            .map(borrowRecordMapper::getByIdResponseFromBorrowRecord)
            .orElseThrow(() -> new NotFoundException("Ödünç kaydı bulunamadı."));
    }
}
