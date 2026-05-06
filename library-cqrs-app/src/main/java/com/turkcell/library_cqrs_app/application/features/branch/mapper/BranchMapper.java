package com.turkcell.library_cqrs_app.application.features.branch.mapper;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.branch.command.create.CreateBranchCommand;
import com.turkcell.library_cqrs_app.application.features.branch.command.create.CreateBranchResponse;
import com.turkcell.library_cqrs_app.application.features.branch.command.update.UpdateBranchCommand;
import com.turkcell.library_cqrs_app.application.features.branch.command.update.UpdateBranchResponse;
import com.turkcell.library_cqrs_app.application.features.branch.query.getall.GetAllBranchResponse;
import com.turkcell.library_cqrs_app.application.features.branch.query.getbyid.GetByIdBranchResponse;
import com.turkcell.library_cqrs_app.domain.entity.Branch;

@Component
public class BranchMapper {

    public Branch branchFromCreateCommand(CreateBranchCommand command) {
        Branch branch = new Branch();
        branch.setName(command.name());
        branch.setAddress(command.address());
        branch.setPhone(command.phone());
        return branch;
    }

    public Branch branchFromUpdateCommand(Branch branch, UpdateBranchCommand command) {
        branch.setName(command.name());
        branch.setAddress(command.address());
        branch.setPhone(command.phone());
        return branch;
    }

    public CreateBranchResponse createResponseFromBranch(Branch branch) {
        return new CreateBranchResponse(
            branch.getId(),
            branch.getName(),
            branch.getAddress(),
            branch.getPhone()
        );
    }

    public UpdateBranchResponse updateResponseFromBranch(Branch branch) {
        return new UpdateBranchResponse(
            branch.getId(),
            branch.getName(),
            branch.getAddress(),
            branch.getPhone()
        );
    }

    public GetAllBranchResponse getAllResponseFromBranch(Branch branch) {
        return new GetAllBranchResponse(
            branch.getId(),
            branch.getName(),
            branch.getAddress(),
            branch.getPhone()
        );
    }

    public GetByIdBranchResponse getByIdResponseFromBranch(Branch branch) {
        return new GetByIdBranchResponse(
            branch.getId(),
            branch.getName(),
            branch.getAddress(),
            branch.getPhone()
        );
    }
}
