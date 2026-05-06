package com.turkcell.library_cqrs_app.application.features.author.mapper;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.command.create.CreateAuthorCommand;
import com.turkcell.library_cqrs_app.application.features.author.command.create.CreateAuthorResponse;
import com.turkcell.library_cqrs_app.application.features.author.command.update.UpdateAuthorCommand;
import com.turkcell.library_cqrs_app.application.features.author.command.update.UpdateAuthorResponse;
import com.turkcell.library_cqrs_app.application.features.author.query.getall.GetAllAuthorResponse;
import com.turkcell.library_cqrs_app.application.features.author.query.getbyid.GetByIdAuthorResponse;
import com.turkcell.library_cqrs_app.domain.entity.Author;

@Component
public class AuthorMapper {
    
    public Author authorFromCreateCommand(CreateAuthorCommand command) {
        Author author = new Author();
        author.setFirstName(command.firstName());
        author.setLastName(command.lastName());
        return author;
    }

    public Author authorFromUpdateCommand(Author author, UpdateAuthorCommand command) {
        author.setFirstName(command.firstName());
        author.setLastName(command.lastName());
        return author;
    }

    public CreateAuthorResponse createResponseFromAuthor(Author author) {
        return new CreateAuthorResponse(
            author.getId(),
            author.getFirstName(),
            author.getLastName()
        );
    }

    public UpdateAuthorResponse updateResponseFromAuthor(Author author) {
        return new UpdateAuthorResponse(
            author.getId(),
            author.getFirstName(),
            author.getLastName()
        );
    }

    public GetAllAuthorResponse getAllResponseFromAuthor(Author author) {
        return new GetAllAuthorResponse(
            author.getId(),
            author.getFirstName(),
            author.getLastName()
        );
    }

    public GetByIdAuthorResponse getByIdResponseFromAuthor(Author author) {
        return new GetByIdAuthorResponse(
            author.getId(),
            author.getFirstName(),
            author.getLastName()
        );
    }
}
