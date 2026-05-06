package com.turkcell.library_cqrs_app.application.features.branch.query.getall;

import java.util.List;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.branch.mapper.BranchMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class GetAllBranchQueryHandler implements QueryHandler<GetAllBranchQuery, List<GetAllBranchResponse>> {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public GetAllBranchQueryHandler(
        BranchRepository branchRepository,
        BranchMapper branchMapper
    ) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public List<GetAllBranchResponse> handle(GetAllBranchQuery query) {
        return branchRepository.findAll().stream()
            .map(branchMapper::getAllResponseFromBranch)
            .toList();
    }

}
