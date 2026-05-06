package com.turkcell.library_cqrs_app.application.features.staff.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.staff.rule.StaffBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;

@Component
public class CreateStaffCommandHandler implements CommandHandler<CreateStaffCommand, CreateStaffResponse>{

private final StaffRepository staffRepository;
    private final StaffBusinessRules staffBusinessRules;

    public CreateStaffCommandHandler(StaffRepository staffRepository,
                                     StaffBusinessRules staffBusinessRules) {
        this.staffRepository = staffRepository;
        this.staffBusinessRules = staffBusinessRules;
    }

    @Override
    public CreateStaffResponse handle(CreateStaffCommand command) {

        staffBusinessRules.staffMustBeUnique(command.firstName(), command.lastName());

        Staff staff = new Staff();
        staff.setFirstName(command.firstName());
        staff.setLastName(command.lastName());
        staff.setRole(command.role());

        Staff saved = staffRepository.save(staff);

        return new CreateStaffResponse(
            saved.getId(),
            saved.getFirstName(),
            saved.getLastName(),
            saved.getRole()
        );
    }

}
