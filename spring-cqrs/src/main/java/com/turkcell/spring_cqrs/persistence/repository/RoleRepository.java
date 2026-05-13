package com.turkcell.spring_cqrs.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.spring_cqrs.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
