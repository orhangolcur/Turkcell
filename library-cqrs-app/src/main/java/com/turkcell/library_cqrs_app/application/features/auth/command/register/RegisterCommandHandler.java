package com.turkcell.library_cqrs_app.application.features.auth.command.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs_app.application.features.auth.rule.UserBusinessRules;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.User;
import com.turkcell.library_cqrs_app.persistence.repository.UserRepository;

@Component
public class RegisterCommandHandler implements CommandHandler<RegisterCommand, RegisterResponse> {

    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;

    public RegisterCommandHandler(UserRepository userRepository, UserBusinessRules userBusinessRules,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse handle(RegisterCommand command) {
        userBusinessRules.emailMustBeUnique(command.email());

        User user = new User();
        user.setEmail(command.email());

        String encodedPassword = passwordEncoder.encode(command.password());
        user.setPassword(encodedPassword);

        User saved = userRepository.save(user);

        return new RegisterResponse(
                saved.getId(),
                saved.getEmail());
    }

}
