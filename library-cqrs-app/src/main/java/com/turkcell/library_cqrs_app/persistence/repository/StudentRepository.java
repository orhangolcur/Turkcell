package com.turkcell.library_cqrs_app.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    boolean existsByEmail(String email);
}