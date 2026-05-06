package com.turkcell.library_cqrs_app.web.controller;

import com.turkcell.library_cqrs_app.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs_app.application.features.student.command.create.CreateStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.command.delete.DeleteStudentCommand;
import com.turkcell.library_cqrs_app.application.features.student.command.delete.DeleteStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.command.update.UpdateStudentCommand;
import com.turkcell.library_cqrs_app.application.features.student.command.update.UpdateStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.query.getall.GetAllStudentQuery;
import com.turkcell.library_cqrs_app.application.features.student.query.getall.GetAllStudentResponse;
import com.turkcell.library_cqrs_app.application.features.student.query.getbyid.GetByIdStudentQuery;
import com.turkcell.library_cqrs_app.application.features.student.query.getbyid.GetByIdStudentResponse;
import com.turkcell.library_cqrs_app.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final Mediator mediator;

    public StudentController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateStudentResponse create(@RequestBody @Valid CreateStudentCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/{id}")
    public UpdateStudentResponse update(@PathVariable UUID id, @RequestBody @Valid UpdateStudentCommand command) {
        return mediator.send(new UpdateStudentCommand(id, command.firstName(), command.lastName(), command.email(), command.phone()));
    }

    @DeleteMapping("/{id}")
    public DeleteStudentResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeleteStudentCommand(id));
    }

    @GetMapping
    public List<GetAllStudentResponse> getAll() {
        return mediator.send(new GetAllStudentQuery());
    }

    @GetMapping("/{id}")
    public GetByIdStudentResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetByIdStudentQuery(id));
    }
}