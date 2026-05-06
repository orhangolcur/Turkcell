package com.turkcell.library_cqrs_app.application.features.branch.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class UpdateBranchCommandHandler implements CommandHandler<UpdateBranchCommand, UpdateBranchResponse> {

    private final BranchRepository branchRepository;
    private final BranchBusinessRules branchBusinessRules;

    public UpdateBranchCommandHandler(BranchRepository branchRepository, BranchBusinessRules branchBusinessRules) {
        this.branchRepository = branchRepository;
        this.branchBusinessRules = branchBusinessRules;
    }

    @Override
    public UpdateBranchResponse handle(UpdateBranchCommand command) {
        Branch branch = branchBusinessRules.getByIdOrThrow(command.id());

        branchBusinessRules.branchNameMustBeUniqueForUpdate(command.id(), command.name());

        branch.setName(command.name());
        branch.setAddress(command.address());
        branch.setPhone(command.phone());

        Branch updatedBranch = branchRepository.save(branch);

        return new UpdateBranchResponse(
            updatedBranch.getId(),
            updatedBranch.getName(),
            updatedBranch.getAddress(),
            updatedBranch.getPhone()
        );
    }

    

}
