package com.turkcell.library_cqrs_app.application.features.student.command.update;

import com.turkcell.library_cqrs_app.application.features.student.mapper.StudentMapper;
import com.turkcell.library_cqrs_app.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Student;
import com.turkcell.library_cqrs_app.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentCommandHandler implements CommandHandler<UpdateStudentCommand, UpdateStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;
    private final StudentMapper studentMapper;

    public UpdateStudentCommandHandler(
        StudentRepository studentRepository,                                       
        StudentBusinessRules studentBusinessRules,                                      
        StudentMapper studentMapper
    ) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
        this.studentMapper = studentMapper;
    }

    @Override
    public UpdateStudentResponse handle(UpdateStudentCommand command) {
        Student student = studentBusinessRules.getByIdOrThrow(command.id());
        studentBusinessRules.emailMustBeUniqueForUpdate(command.id(), command.email());

        studentMapper.studentFromUpdateCommand(student, command);
        Student saved = studentRepository.save(student);

        return studentMapper.updateResponseFromStudent(saved);
    }
}