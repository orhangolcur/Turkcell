package com.turkcell.library_cqrs_app.application.features.borrowrecord.query.getall;

import com.turkcell.library_cqrs_app.application.features.borrowrecord.mapper.BorrowRecordMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BorrowRecordRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllBorrowRecordQueryHandler implements QueryHandler<GetAllBorrowRecordQuery, List<GetAllBorrowRecordResponse>> {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    public GetAllBorrowRecordQueryHandler(
        BorrowRecordRepository borrowRecordRepository,
        BorrowRecordMapper borrowRecordMapper
    ) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @Override
    public List<GetAllBorrowRecordResponse> handle(GetAllBorrowRecordQuery query) {
        return borrowRecordRepository.findAll().stream()
            .map(borrowRecordMapper::getAllResponseFromBorrowRecord)
            .toList();
    }
}
