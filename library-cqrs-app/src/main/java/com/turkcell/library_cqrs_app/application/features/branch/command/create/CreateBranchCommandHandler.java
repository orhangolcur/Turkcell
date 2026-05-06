package com.turkcell.library_cqrs_app.application.features.branch.command.create;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.branch.mapper.BranchMapper;
import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class CreateBranchCommandHandler implements CommandHandler<CreateBranchCommand, CreateBranchResponse> {

    private final BranchRepository branchRepository;
    private final BranchBusinessRules branchBusinessRules;
    private final BranchMapper branchMapper;

    public CreateBranchCommandHandler(
        BranchRepository branchRepository, 
        BranchBusinessRules branchBusinessRules,
        BranchMapper branchMapper
    ) {
        this.branchRepository = branchRepository;
        this.branchBusinessRules = branchBusinessRules;
        this.branchMapper = branchMapper;
    }

    @Override
    public CreateBranchResponse handle(CreateBranchCommand command) {
        branchBusinessRules.branchNameMustBeUnique(command.name());

        Branch branch = branchMapper.branchFromCreateCommand(command);

        Branch savedBranch = branchRepository.save(branch);

        return branchMapper.createResponseFromBranch(savedBranch);
    }

}
