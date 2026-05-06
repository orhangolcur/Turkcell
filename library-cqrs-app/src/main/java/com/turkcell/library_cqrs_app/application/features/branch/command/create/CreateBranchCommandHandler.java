package com.turkcell.library_cqrs_app.application.features.branch.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class CreateBranchCommandHandler implements CommandHandler<CreateBranchCommand, CreateBranchResponse> {

    private final BranchRepository branchRepository;
    private final BranchBusinessRules branchBusinessRules;

    public CreateBranchCommandHandler(BranchRepository branchRepository, BranchBusinessRules branchBusinessRules) {
        this.branchRepository = branchRepository;
        this.branchBusinessRules = branchBusinessRules;
    }

    @Override
    public CreateBranchResponse handle(CreateBranchCommand command) {
        branchBusinessRules.branchNameMustBeUnique(command.name());

        Branch branch = new Branch();
        branch.setName(command.name());
        branch.setAddress(command.address());
        branch.setPhone(command.phone());

        Branch savedBranch = branchRepository.save(branch);

        return new CreateBranchResponse(
            savedBranch.getId(),
            savedBranch.getName(),
            savedBranch.getAddress(),
            savedBranch.getPhone()
        );
    }

}
