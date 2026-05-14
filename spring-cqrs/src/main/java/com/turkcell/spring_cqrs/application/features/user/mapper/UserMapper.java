package com.turkcell.spring_cqrs.application.features.user.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.turkcell.spring_cqrs.application.features.user.command.register.RegisterCommand;
import com.turkcell.spring_cqrs.application.features.user.command.register.RegisterResponse;
import com.turkcell.spring_cqrs.domain.Role;
import com.turkcell.spring_cqrs.domain.User;

@Component
public class UserMapper {

    public User userFromRegisterCommand(RegisterCommand command, String encodedPassword, Role role) {
        User user = new User();
        user.setEmail(command.email());
        user.setPassword(encodedPassword);
        user.setRoles(List.of(role));
        return user;
    }

    public RegisterResponse registerResponseFromUser(User user) {
        return new RegisterResponse(user.getId(), user.getEmail());
    }

}
