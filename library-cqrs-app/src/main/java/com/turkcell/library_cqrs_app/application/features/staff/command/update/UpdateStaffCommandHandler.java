package com.turkcell.library_cqrs_app.application.features.staff.command.update;

import com.turkcell.library_cqrs_app.application.features.staff.rule.StaffBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateStaffCommandHandler implements CommandHandler<UpdateStaffCommand, UpdateStaffResponse> {

    private final StaffRepository staffRepository;
    private final StaffBusinessRules staffBusinessRules;

    public UpdateStaffCommandHandler(StaffRepository staffRepository,
                                     StaffBusinessRules staffBusinessRules) {
        this.staffRepository = staffRepository;
        this.staffBusinessRules = staffBusinessRules;
    }

    @Override
    public UpdateStaffResponse handle(UpdateStaffCommand command) {

        Staff staff = staffBusinessRules.getByIdOrThrow(command.id());
        staffBusinessRules.staffMustBeUniqueForUpdate(command.id(), command.firstName(), command.lastName());

        staff.setFirstName(command.firstName());
        staff.setLastName(command.lastName());
        staff.setRole(command.role());

        Staff saved = staffRepository.save(staff);

        return new UpdateStaffResponse(
            saved.getId(),
            saved.getFirstName(),
            saved.getLastName(),
            saved.getRole()
        );
    }
}