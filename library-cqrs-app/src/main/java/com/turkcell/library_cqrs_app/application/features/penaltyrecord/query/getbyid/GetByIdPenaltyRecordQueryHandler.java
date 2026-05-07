package com.turkcell.library_cqrs_app.application.features.penaltyrecord.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.penaltyrecord.mapper.PenaltyRecordMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.PenaltyRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdPenaltyRecordQueryHandler implements QueryHandler<GetByIdPenaltyRecordQuery, GetByIdPenaltyRecordResponse> {

    private final PenaltyRecordRepository penaltyRecordRepository;
    private final PenaltyRecordMapper penaltyRecordMapper;

    public GetByIdPenaltyRecordQueryHandler(
        PenaltyRecordRepository penaltyRecordRepository,
        PenaltyRecordMapper penaltyRecordMapper
    ) {
        this.penaltyRecordRepository = penaltyRecordRepository;
        this.penaltyRecordMapper = penaltyRecordMapper;
    }

    @Override
    public GetByIdPenaltyRecordResponse handle(GetByIdPenaltyRecordQuery query) {
        return penaltyRecordRepository.findById(query.id())
            .map(penaltyRecordMapper::getByIdResponseFromPenaltyRecord)
            .orElseThrow(() -> new NotFoundException("Ceza kaydı bulunamadı."));
    }
}
