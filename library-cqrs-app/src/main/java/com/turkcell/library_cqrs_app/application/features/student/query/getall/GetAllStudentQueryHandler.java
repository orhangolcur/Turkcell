package com.turkcell.library_cqrs_app.application.features.student.query.getall;

import com.turkcell.library_cqrs_app.application.features.student.mapper.StudentMapper;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllStudentQueryHandler implements QueryHandler<GetAllStudentQuery, List<GetAllStudentResponse>> {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public GetAllStudentQueryHandler(StudentRepository studentRepository,
                                     StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<GetAllStudentResponse> handle(GetAllStudentQuery query) {
        return studentRepository.findAll().stream()
            .map(studentMapper::getAllResponseFromStudent)
            .toList();
    }
}