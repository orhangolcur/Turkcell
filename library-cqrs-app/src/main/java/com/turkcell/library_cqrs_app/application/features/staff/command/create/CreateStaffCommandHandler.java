package com.turkcell.library_cqrs_app.application.features.staff.command.create;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.staff.mapper.StaffMapper;
import com.turkcell.library_cqrs_app.application.features.staff.rule.StaffBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;

@Component
public class CreateStaffCommandHandler implements CommandHandler<CreateStaffCommand, CreateStaffResponse>{

    private final StaffRepository staffRepository;
    private final StaffBusinessRules staffBusinessRules;
    private final StaffMapper staffMapper;

    public CreateStaffCommandHandler(
        StaffRepository staffRepository,
        StaffBusinessRules staffBusinessRules,
        StaffMapper staffMapper
    ) {
        this.staffRepository = staffRepository;
        this.staffBusinessRules = staffBusinessRules;
        this.staffMapper = staffMapper;
    }

    @Override
    public CreateStaffResponse handle(CreateStaffCommand command) {

        staffBusinessRules.staffMustBeUnique(command.firstName(), command.lastName());

        Staff staff = staffMapper.staffFromCreateCommand(command);

        Staff savedStaff = staffRepository.save(staff);

        return staffMapper.createResponseFromStaff(savedStaff);
    }

}
