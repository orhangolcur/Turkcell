package com.turkcell.spring_cqrs.application.features.user.command.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.turkcell.spring_cqrs.application.features.user.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public RegisterCommandHandler(
        UserRepository userRepository,
        RoleBusinessRules roleBusinessRules,
        UserBusinessRules userBusinessRules,
        PasswordEncoder passwordEncoder,
        UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.roleBusinessRules = roleBusinessRules;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public RegisterResponse handle(RegisterCommand command) {
        userBusinessRules.userWithSameEmailMustNotExist(command.email());

        Role userRole = roleBusinessRules.getRoleByNameOrThrow("USER");

        String encodedPassword = passwordEncoder.encode(command.password());

        User user = userMapper.userFromRegisterCommand(command, encodedPassword, userRole);
        
        userRepository.save(user);

        return userMapper.registerResponseFromUser(user);
    }

}
