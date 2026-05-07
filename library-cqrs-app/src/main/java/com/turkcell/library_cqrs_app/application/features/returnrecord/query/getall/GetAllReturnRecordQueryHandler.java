package com.turkcell.library_cqrs_app.application.features.returnrecord.query.getall;

import com.turkcell.library_cqrs_app.application.features.returnrecord.mapper.ReturnRecordMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.ReturnRecordRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllReturnRecordQueryHandler implements QueryHandler<GetAllReturnRecordQuery, List<GetAllReturnRecordResponse>> {

    private final ReturnRecordRepository returnRecordRepository;
    private final ReturnRecordMapper returnRecordMapper;

    public GetAllReturnRecordQueryHandler(ReturnRecordRepository returnRecordRepository,
                                          ReturnRecordMapper returnRecordMapper) {
        this.returnRecordRepository = returnRecordRepository;
        this.returnRecordMapper = returnRecordMapper;
    }

    @Override
    public List<GetAllReturnRecordResponse> handle(GetAllReturnRecordQuery query) {
        return returnRecordRepository.findAll().stream()
            .map(returnRecordMapper::getAllResponseFromReturnRecord)
            .toList();
    }
}
