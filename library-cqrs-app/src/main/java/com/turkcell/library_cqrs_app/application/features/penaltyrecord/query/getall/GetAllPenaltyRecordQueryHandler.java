package com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getall;

import com.turkcell.library_cqrs_app.application.features.penaltyrecord.mapper.PenaltyRecordMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.PenaltyRecordRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllPenaltyRecordQueryHandler implements QueryHandler<GetAllPenaltyRecordQuery, List<GetAllPenaltyRecordResponse>> {

    private final PenaltyRecordRepository penaltyRecordRepository;
    private final PenaltyRecordMapper penaltyRecordMapper;

    public GetAllPenaltyRecordQueryHandler(
        PenaltyRecordRepository penaltyRecordRepository,                                 
        PenaltyRecordMapper penaltyRecordMapper
    ) {
        this.penaltyRecordRepository = penaltyRecordRepository;
        this.penaltyRecordMapper = penaltyRecordMapper;
    }

    @Override
    public List<GetAllPenaltyRecordResponse> handle(GetAllPenaltyRecordQuery query) {
        return penaltyRecordRepository.findAll().stream()
            .map(penaltyRecordMapper::getAllResponseFromPenaltyRecord)
            .toList();
    }
}
