package com.turkcell.library_cqrs_app.application.features.branch.command.update;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.branch.mapper.BranchMapper;
import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class UpdateBranchCommandHandler implements CommandHandler<UpdateBranchCommand, UpdateBranchResponse> {

    private final BranchRepository branchRepository;
    private final BranchBusinessRules branchBusinessRules;
    private final BranchMapper branchMapper;

    public UpdateBranchCommandHandler(
        BranchRepository branchRepository, 
        BranchBusinessRules branchBusinessRules,
        BranchMapper branchMapper
    ) {
        this.branchRepository = branchRepository;
        this.branchBusinessRules = branchBusinessRules;
        this.branchMapper = branchMapper;
    }

    @Override
    public UpdateBranchResponse handle(UpdateBranchCommand command) {
        Branch branch = branchBusinessRules.getByIdOrThrow(command.id());

        branchBusinessRules.branchNameMustBeUniqueForUpdate(command.id(), command.name());

        branchMapper.branchFromUpdateCommand(branch, command);

        Branch updatedBranch = branchRepository.save(branch);

        return branchMapper.updateResponseFromBranch(updatedBranch);
    }
}
