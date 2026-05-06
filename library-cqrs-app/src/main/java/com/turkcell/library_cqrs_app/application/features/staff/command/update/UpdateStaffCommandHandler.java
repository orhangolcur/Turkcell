package com.turkcell.library_cqrs_app.application.features.staff.command.update;

import com.turkcell.library_cqrs_app.application.features.staff.mapper.StaffMapper;
import com.turkcell.library_cqrs_app.application.features.staff.rule.StaffBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateStaffCommandHandler implements CommandHandler<UpdateStaffCommand, UpdateStaffResponse> {

    private final StaffRepository staffRepository;
    private final StaffBusinessRules staffBusinessRules;
    private final StaffMapper staffMapper;

    public UpdateStaffCommandHandler(
        StaffRepository staffRepository,
        StaffBusinessRules staffBusinessRules,
        StaffMapper staffMapper
    ) {
        this.staffRepository = staffRepository;
        this.staffBusinessRules = staffBusinessRules;
        this.staffMapper = staffMapper;
    }

    @Override
    public UpdateStaffResponse handle(UpdateStaffCommand command) {

        Staff staff = staffBusinessRules.getByIdOrThrow(command.id());
        staffBusinessRules.staffMustBeUniqueForUpdate(command.id(), command.firstName(), command.lastName());

        staffMapper.staffFromUpdateCommand(staff, command);

        Staff savedStaff = staffRepository.save(staff);

        return staffMapper.updateResponseFromStaff(savedStaff);
    }
}