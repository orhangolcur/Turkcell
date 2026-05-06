package com.turkcell.library_cqrs_app.application.features.author.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.author.rule.AuthorBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class DeleteAuthorCommandHandler implements CommandHandler<DeleteAuthorCommand, DeleteAuthorResponse> {

    private final AuthorRepository repository;
    private final AuthorBusinessRules authorBusinessRules;

    public DeleteAuthorCommandHandler(AuthorRepository repository, AuthorBusinessRules authorBusinessRules) {
        this.repository = repository;
        this.authorBusinessRules = authorBusinessRules;
    }

    @Override
    public DeleteAuthorResponse handle(DeleteAuthorCommand command) {
        Author author = authorBusinessRules.getByIdOrThrow(command.id());

        repository.delete(author);

        return new DeleteAuthorResponse(
                author.getId(),
                "Yazar başarıyla silindi");
    }

}
