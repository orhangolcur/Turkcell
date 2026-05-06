package com.turkcell.library_cqrs_app.application.features.staff.rule;

import java.util.UUID;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.Staff;
import com.turkcell.library_cqrs_app.persistence.repository.StaffRepository;

@Component
public class StaffBusinessRules {

    private final StaffRepository staffRepository;

    public StaffBusinessRules(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff getByIdOrThrow(UUID id) {
        return staffRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Görevli bulunamadı"));
    }

        public void staffMustBeUnique(String firstName, String lastName) {
        if (staffRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new AlreadyExistsException("Bu isimde bir görevli zaten mevcut.");
        }
    }

    public void staffMustBeUniqueForUpdate(UUID id, String firstName, String lastName) {
        staffRepository.findById(id).ifPresent(staff -> {
            if ((!staff.getFirstName().equals(firstName) || !staff.getLastName().equals(lastName))
                    && staffRepository.existsByFirstNameAndLastName(firstName, lastName)) {
                throw new AlreadyExistsException("Bu isimde bir görevli zaten mevcut.");
            }
        });
    }

}
