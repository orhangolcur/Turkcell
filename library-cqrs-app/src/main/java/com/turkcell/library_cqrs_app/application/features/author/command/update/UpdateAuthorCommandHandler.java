package com.turkcell.library_cqrs_app.application.features.author.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class UpdateAuthorCommandHandler implements CommandHandler<UpdateAuthorCommand, UpdateAuthorResponse> {

    private final AuthorRepository authorRepository;
    private final AuthorBusinessRules authorBusinessRules;

    public UpdateAuthorCommandHandler(AuthorRepository authorRepository, AuthorBusinessRules authorBusinessRules) {
        this.authorRepository = authorRepository;
        this.authorBusinessRules = authorBusinessRules;
    }

    @Override
    public UpdateAuthorResponse handle(UpdateAuthorCommand command) {
        Author author = authorBusinessRules.getByIdOrThrow(command.id());

        author.setFirstName(command.firstName());
        author.setLastName(command.lastName());

        Author updatedAuthor = authorRepository.save(author);

        return new UpdateAuthorResponse(
                updatedAuthor.getId(),
                updatedAuthor.getFirstName(),
                updatedAuthor.getLastName());
    }
}
