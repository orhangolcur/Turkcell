package com.turkcell.library_cqrs_app.application.features.staff.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.staff.mapper.StaffMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdStaffQueryHandler implements QueryHandler<GetByIdStaffQuery, GetByIdStaffResponse> {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public GetByIdStaffQueryHandler(
        StaffRepository staffRepository,
        StaffMapper staffMapper
    ) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public GetByIdStaffResponse handle(GetByIdStaffQuery query) {
        return staffRepository.findById(query.id())
            .map(staffMapper::getByIdResponseFromStaff)
            .orElseThrow(() -> new NotFoundException("Görevli bulunamadı."));
    }
}
