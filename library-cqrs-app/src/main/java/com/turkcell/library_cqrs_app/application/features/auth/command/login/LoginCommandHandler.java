package com.turkcell.library_cqrs_app.application.features.auth.command.login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.auth.rule.UserBusinessRules;
import com.turkcell.library_cqrs_app.core.exception.BusinessException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.domain.entity.User;

@Component
public class LoginCommandHandler implements CommandHandler<LoginCommand, LoginResponse> {

    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;

    public LoginCommandHandler(UserBusinessRules userBusinessRules,
            PasswordEncoder passwordEncoder) {
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse handle(LoginCommand command) {

        User user = userBusinessRules.getUserByEmailOrThrow(command.email());

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new BusinessException("Giriş bilgileri yanlış");
        }
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                "Giriş başarılı");
    }

}
