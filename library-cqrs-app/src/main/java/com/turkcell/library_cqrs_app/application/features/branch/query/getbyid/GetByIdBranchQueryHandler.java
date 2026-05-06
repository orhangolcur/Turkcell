package com.turkcell.library_cqrs_app.application.features.branch.query.getbyid;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.branch.mapper.BranchMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class GetByIdBranchQueryHandler implements QueryHandler<GetByIdBranchQuery, GetByIdBranchResponse>{

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public GetByIdBranchQueryHandler(
        BranchRepository branchRepository,
        BranchMapper branchMapper
    ) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public GetByIdBranchResponse handle(GetByIdBranchQuery query) {
        return branchRepository.findById(query.id())
            .map(branchMapper::getByIdResponseFromBranch)
            .orElseThrow(() -> new NotFoundException("Şube bulunamadı."));
    }

}
