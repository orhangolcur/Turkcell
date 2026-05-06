package com.turkcell.library_cqrs_app.application.features.author.rule;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.core.exception.AlreadyExistsException;
import com.turkcell.library_cqrs_app.core.exception.NotFoundException;
import com.turkcell.library_cqrs_app.domain.entity.Author;
import com.turkcell.library_cqrs_app.persistence.repository.AuthorRepository;

@Component
public class AuthorBusinessRules {

    private final AuthorRepository authorRepository;

    public AuthorBusinessRules(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllByIdsOrThrow(List<UUID> ids) {
        List<Author> authors = authorRepository.findAllById(ids);
        if (authors.size() != ids.size()) {
            throw new NotFoundException("Bir veya daha fazla yazar bulunamadı.");
        }
        return authors;
    }

    public Author getByIdOrThrow(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yazar bulunamadı"));
    }

    public void authorNameMustBeUnique(String firstName, String lastName) {
        if (authorRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new AlreadyExistsException("Bu isimde bir yazar zaten mevcut: " + firstName + " " + lastName);
        }
    }

    public void authorMustExist(UUID id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Yazar bulunamadı");
        }
    }
}
