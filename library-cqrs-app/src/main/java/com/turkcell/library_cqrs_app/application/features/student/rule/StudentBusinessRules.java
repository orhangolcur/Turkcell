package com.turkcell.library_cqrs_app.application.features.student.rule;

import java.util.UUID;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.StudentRepository;

@Component
public class StudentBusinessRules {

    private final StudentRepository studentRepository;

    public StudentBusinessRules(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getByIdOrThrow(UUID id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Öğrenci bulunamadı."));
    }

    public void emailMustBeUnique(String email) {
        if (studentRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Bu email zaten kayıtlı.");
        }
    }

    public void emailMustBeUniqueForUpdate(UUID id, String email) {
        studentRepository.findById(id).ifPresent(student -> {
            if (!student.getEmail().equals(email) && studentRepository.existsByEmail(email)) {
                throw new AlreadyExistsException("Bu email zaten kayıtlı");
            }
        });
    }
}
