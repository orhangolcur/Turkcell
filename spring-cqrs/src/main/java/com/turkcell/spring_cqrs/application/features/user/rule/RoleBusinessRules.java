package com.turkcell.spring_cqrs.application.features.user.rule;

import org.springframework.stereotype.Component;
import com.turkcell.spring_cqrs.core.exception.NotFoundException;
import com.turkcell.spring_cqrs.domain.Role;
import com.turkcell.spring_cqrs.persistence.repository.RoleRepository;

@Component
public class RoleBusinessRules {

    private final RoleRepository roleRepository;

    public RoleBusinessRules(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("'" + name + "' rolü bulunamadı."));
    }
}
