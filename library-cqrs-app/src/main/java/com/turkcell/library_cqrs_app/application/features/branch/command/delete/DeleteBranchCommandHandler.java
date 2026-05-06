package com.turkcell.library_cqrs_app.application.features.branch.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.branch.rule.BranchBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class DeleteBranchCommandHandler implements CommandHandler<DeleteBranchCommand, DeleteBranchResponse> {

    private final BranchRepository branchRepository;
    private final BranchBusinessRules branchBusinessRules;

    public DeleteBranchCommandHandler(BranchRepository branchRepository, BranchBusinessRules branchBusinessRules) {
        this.branchRepository = branchRepository;
        this.branchBusinessRules = branchBusinessRules;
    }

    @Override
    public DeleteBranchResponse handle(DeleteBranchCommand command) {
        Branch branch = branchBusinessRules.getByIdOrThrow(command.id());

        branchRepository.delete(branch);

        return new DeleteBranchResponse(
            branch.getId(),
            "Şube başarıyla silindi"
        );
    }

}
