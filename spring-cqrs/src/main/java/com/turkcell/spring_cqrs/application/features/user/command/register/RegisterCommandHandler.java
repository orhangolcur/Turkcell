package com.turkcell.spring_cqrs.application.features.user.command.register;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.application.features.user.rule.RoleBusinessRules;
import com.turkcell.spring_cqrs.application.features.user.rule.UserBusinessRules;
import com.turkcell.spring_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.spring_cqrs.domain.Role;
import com.turkcell.spring_cqrs.domain.User;
import com.turkcell.spring_cqrs.persistence.repository.UserRepository;

@Component
public class RegisterCommandHandler implements CommandHandler<RegisterCommand, RegisterResponse> {

    private final UserRepository userRepository;
    private final RoleBusinessRules roleBusinessRules;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;

    public RegisterCommandHandler(UserRepository userRepository, RoleBusinessRules roleBusinessRules,
            UserBusinessRules userBusinessRules, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleBusinessRules = roleBusinessRules;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse handle(RegisterCommand command) {
        this.userBusinessRules.userWithSameEmailMustNotExist(command.email());

        Role userRole = roleBusinessRules.getByName("USER");

        User user = new User();
        user.setEmail(command.email());
        user.setPassword(passwordEncoder.encode(command.password()));
        user.setRoles(List.of(userRole));

        userRepository.save(user);

        return new RegisterResponse(user.getId(), user.getEmail());
    }

}
