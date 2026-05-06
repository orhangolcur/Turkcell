package com.turkcell.library_cqrs_app.application.features.student.command.delete;

import com.turkcell.library_cqrs_app.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteStudentCommandHandler implements CommandHandler<DeleteStudentCommand, DeleteStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;

    public DeleteStudentCommandHandler(StudentRepository studentRepository,
                                       StudentBusinessRules studentBusinessRules) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
    }

    @Override
    public DeleteStudentResponse handle(DeleteStudentCommand command) {
        Student student = studentBusinessRules.getByIdOrThrow(command.id());
        studentRepository.delete(student);

        return new DeleteStudentResponse(
            command.id(),
            "Öğrenci başarıyla silindi."
        );
    }
}