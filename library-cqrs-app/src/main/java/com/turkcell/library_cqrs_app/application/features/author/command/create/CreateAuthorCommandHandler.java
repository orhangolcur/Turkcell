package com.turkcell.library_cqrs_app.application.features.author.command.create;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.mapper.AuthorMapper;
import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, CreateAuthorResponse> {

    private final AuthorRepository repository;
    private final AuthorBusinessRules authorBusinessRules;
    private final AuthorMapper authorMapper;

    public CreateAuthorCommandHandler(
        AuthorRepository repository, 
        AuthorBusinessRules authorBusinessRules,
        AuthorMapper authorMapper
    ) {
        this.repository = repository;
        this.authorBusinessRules = authorBusinessRules;
        this.authorMapper = authorMapper;
    }

    @Override
    public CreateAuthorResponse handle(CreateAuthorCommand command) {
        authorBusinessRules.authorNameMustBeUnique(command.firstName(), command.lastName());

        Author author = authorMapper.authorFromCreateCommand(command);

        Author savedAuthor = repository.save(author);

        return authorMapper.createResponseFromAuthor(savedAuthor);
    }
}
