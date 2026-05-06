package com.turkcell.library_cqrs_app.application.features.staff.mapper;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.staff.command.create.CreateStaffCommand;
import com.turkcell.library_cqrs_app.application.features.staff.command.create.CreateStaffResponse;
import com.turkcell.library_cqrs_app.application.features.staff.command.update.UpdateStaffCommand;
import com.turkcell.library_cqrs_app.application.features.staff.command.update.UpdateStaffResponse;
import com.turkcell.library_cqrs_app.application.features.staff.query.getall.GetAllStaffResponse;
import com.turkcell.library_cqrs_app.application.features.staff.query.getbyid.GetByIdStaffResponse;
import com.turkcell.library_cqrs_app.domain.entity.Staff;

@Component
public class StaffMapper {

    public Staff staffFromCreateCommand(CreateStaffCommand command) {
        Staff staff = new Staff();
        staff.setFirstName(command.firstName());
        staff.setLastName(command.lastName());
        staff.setRole(command.role());
        return staff;
    }

    public Staff staffFromUpdateCommand(Staff staff, UpdateStaffCommand command) {
        staff.setFirstName(command.firstName());
        staff.setLastName(command.lastName());
        staff.setRole(command.role());
        return staff;
    }

    public CreateStaffResponse createResponseFromStaff(Staff staff) {
        return new CreateStaffResponse(
            staff.getId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getRole()
        );
    }

    public UpdateStaffResponse updateResponseFromStaff(Staff staff) {
        return new UpdateStaffResponse(
            staff.getId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getRole()
        );
    }

    public GetAllStaffResponse getAllResponseFromStaff(Staff staff) {
        return new GetAllStaffResponse(
            staff.getId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getRole()
        );
    }

    public GetByIdStaffResponse getByIdResponseFromStaff(Staff staff) {
        return new GetByIdStaffResponse(
            staff.getId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getRole()
        );
    }

}
