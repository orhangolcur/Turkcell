package com.turkcell.library_cqrs_app.application.features.student.command.create;

import com.turkcell.library_cqrs_app.application.features.student.mapper.StudentMapper;
import com.turkcell.library_cqrs_app.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, CreateStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;
    private final StudentMapper studentMapper;

    public CreateStudentCommandHandler(
        StudentRepository studentRepository,
        StudentBusinessRules studentBusinessRules,                                      StudentMapper studentMapper
    ) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
        this.studentMapper = studentMapper;
    }

    @Override
    public CreateStudentResponse handle(CreateStudentCommand command) {
        studentBusinessRules.emailMustBeUnique(command.email());

        Student student = studentMapper.studentFromCreateCommand(command);
        Student saved = studentRepository.save(student);

        return studentMapper.createResponseFromStudent(saved);
    }
}