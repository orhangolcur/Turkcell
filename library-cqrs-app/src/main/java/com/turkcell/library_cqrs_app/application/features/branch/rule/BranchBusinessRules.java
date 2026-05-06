package com.turkcell.library_cqrs_app.application.features.branch.rule;

import java.util.UUID;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.Branch;
import com.turkcell.library_cqrs_app.persistence.repository.BranchRepository;

@Component
public class BranchBusinessRules {

    private final BranchRepository branchRepository;

    public BranchBusinessRules(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public void branchNameMustBeUnique(String name) {
        if (branchRepository.existsByName(name)) {
            throw new AlreadyExistsException("Şube adı zaten mevcut");
        }
    }

    public void branchNameMustBeUniqueForUpdate(UUID id, String name) {
        branchRepository.findById(id).ifPresent(branch -> {
            if (!branch.getName().equals(name) && branchRepository.existsByName(name)) {
                throw new AlreadyExistsException("Bu isimde bir şube zaten mevcut");
            }
        });
    }

    public Branch getByIdOrThrow(UUID id) {
        return branchRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Şube bulunamadı"));
    }
}
