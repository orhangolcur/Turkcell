package com.turkcell.library_cqrs_app.application.features.staff.query.getall;

import com.turkcell.library_cqrs_app.application.features.staff.mapper.StaffMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllStaffQueryHandler implements QueryHandler<GetAllStaffQuery, List<GetAllStaffResponse>> {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public GetAllStaffQueryHandler(
        StaffRepository staffRepository,
        StaffMapper staffMapper
    ) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public List<GetAllStaffResponse> handle(GetAllStaffQuery query) {
        return staffRepository.findAll().stream()
            .map(staffMapper::getAllResponseFromStaff)
            .toList();
    }
}