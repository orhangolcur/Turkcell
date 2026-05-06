package com.turkcell.library_cqrs_app.application.features.staff.command.delete;

import com.turkcell.library_cqrs_app.application.features.staff.rule.StaffBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteStaffCommandHandler implements CommandHandler<DeleteStaffCommand, DeleteStaffResponse> {

    private final StaffRepository staffRepository;
    private final StaffBusinessRules staffBusinessRules;

    public DeleteStaffCommandHandler(StaffRepository staffRepository,
                                     StaffBusinessRules staffBusinessRules) {
        this.staffRepository = staffRepository;
        this.staffBusinessRules = staffBusinessRules;
    }

    @Override
    public DeleteStaffResponse handle(DeleteStaffCommand command) {

        Staff staff = staffBusinessRules.getByIdOrThrow(command.id());
        staffRepository.delete(staff);

        return new DeleteStaffResponse(
            command.id(),
            "Görevli başarıyla silindi."
        );
    }
}