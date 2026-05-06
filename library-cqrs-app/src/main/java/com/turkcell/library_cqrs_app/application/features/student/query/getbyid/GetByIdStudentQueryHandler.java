package com.turkcell.library_cqrs_app.application.features.student.query.getbyid;

import com.turkcell.library_cqrs_app.application.features.student.mapper.StudentMapper;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs_app.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class GetByIdStudentQueryHandler implements QueryHandler<GetByIdStudentQuery, GetByIdStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public GetByIdStudentQueryHandler(StudentRepository studentRepository,
                                      StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public GetByIdStudentResponse handle(GetByIdStudentQuery query) {
        return studentRepository.findById(query.id())
            .map(studentMapper::getByIdResponseFromStudent)
            .orElseThrow(() -> new NotFoundException("Öğrenci bulunamadı."));
    }
}