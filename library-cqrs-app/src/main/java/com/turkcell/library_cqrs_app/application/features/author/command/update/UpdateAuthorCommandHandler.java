package com.turkcell.library_cqrs_app.application.features.author.command.update;

import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.author.mapper.AuthorMapper;
import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class UpdateAuthorCommandHandler implements CommandHandler<UpdateAuthorCommand, UpdateAuthorResponse> {

    private final AuthorRepository authorRepository;
    private final AuthorBusinessRules authorBusinessRules;
    private final AuthorMapper authorMapper;

    public UpdateAuthorCommandHandler(
        AuthorRepository authorRepository, 
        AuthorBusinessRules authorBusinessRules,
        AuthorMapper authorMapper    
    ) {
        this.authorRepository = authorRepository;
        this.authorBusinessRules = authorBusinessRules;
        this.authorMapper = authorMapper;
    }

    @Override
    public UpdateAuthorResponse handle(UpdateAuthorCommand command) {
        Author author = authorBusinessRules.getByIdOrThrow(command.id());

        authorMapper.authorFromUpdateCommand(author, command);

        Author updatedAuthor = authorRepository.save(author);

        return authorMapper.updateResponseFromAuthor(updatedAuthor);
    }
}
