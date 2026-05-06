package com.turkcell.library_cqrs_app.application.features.student.mapper;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs_app.application.features.student.command.create.CreateStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.command.update.UpdateStudentCommand;
import com.turkcell.library_cqrs_app.application.features.student.command.update.UpdateStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.query.getall.GetAllStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.query.getbyid.GetByIdStudentResponse;
import com.turkcell.library_cqrs_app.domain.entity.Student;

@Component
public class StudentMapper {

    public Student studentFromCreateCommand(CreateStudentCommand command) {
        Student student = new Student();
        student.setFirstName(command.firstName());
        student.setLastName(command.lastName());
        student.setEmail(command.email());
        student.setPhone(command.phone());
        student.setMembershipDate(command.membershipDate());
        return student;
    }

    public Student studentFromUpdateCommand(Student student, UpdateStudentCommand command) {
        student.setFirstName(command.firstName());
        student.setLastName(command.lastName());
        student.setEmail(command.email());
        student.setPhone(command.phone());
        return student;
    }

    public CreateStudentResponse createResponseFromStudent(Student student) {
        return new CreateStudentResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getPhone(),
            student.getMembershipDate()
        );
    }

    public UpdateStudentResponse updateResponseFromStudent(Student student) {
        return new UpdateStudentResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getPhone(),
            student.getMembershipDate()
        );
    }

    public GetAllStudentResponse getAllResponseFromStudent(Student student) {
        return new GetAllStudentResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getPhone(),
            student.getMembershipDate()
        );
    }

    public GetByIdStudentResponse getByIdResponseFromStudent(Student student) {
        return new GetByIdStudentResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getPhone(),
            student.getMembershipDate()
        );
    }
}
