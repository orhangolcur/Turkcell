package com.turkcell.library_cqrs_app.application.features.returnrecord.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.returnrecord.mapper.ReturnRecordMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.ReturnRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdReturnRecordQueryHandler implements QueryHandler<GetByIdReturnRecordQuery, GetByIdReturnRecordResponse> {

    private final ReturnRecordRepository returnRecordRepository;
    private final ReturnRecordMapper returnRecordMapper;

    public GetByIdReturnRecordQueryHandler(ReturnRecordRepository returnRecordRepository,
                                           ReturnRecordMapper returnRecordMapper) {
        this.returnRecordRepository = returnRecordRepository;
        this.returnRecordMapper = returnRecordMapper;
    }

    @Override
    public GetByIdReturnRecordResponse handle(GetByIdReturnRecordQuery query) {
        return returnRecordRepository.findById(query.id())
            .map(returnRecordMapper::getByIdResponseFromReturnRecord)
            .orElseThrow(() -> new NotFoundException("İade kaydı bulunamadı."));
    }
}
