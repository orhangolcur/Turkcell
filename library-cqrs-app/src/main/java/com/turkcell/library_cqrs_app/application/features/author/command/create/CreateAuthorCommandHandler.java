package com.turkcell.library_cqrs_app.application.features.author.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, CreateAuthorResponse> {

    private final AuthorRepository repository;
    private final AuthorBusinessRules authorBusinessRules;

    public CreateAuthorCommandHandler(AuthorRepository repository, AuthorBusinessRules authorBusinessRules) {
        this.repository = repository;
        this.authorBusinessRules = authorBusinessRules;
    }

    @Override
    public CreateAuthorResponse handle(CreateAuthorCommand command) {
        authorBusinessRules.authorNameMustBeUnique(command.firstName(), command.lastName());

        Author author = new Author();
        author.setFirstName(command.firstName());
        author.setLastName(command.lastName());

        Author savedAuthor = repository.save(author);

        return new CreateAuthorResponse(
                savedAuthor.getId(),
                savedAuthor.getFirstName(),
                savedAuthor.getLastName());
    }

}
