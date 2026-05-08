package com.turkcell.library_cqrs_app.application.features.auth.command.login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.application.features.auth.rule.UserBusinessRules;
import com.turkcell.library_cqrs_app.core.exception.BusinessException;
import com.turkcell.library_cqrs_app.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs_app.core.security.jwt.JwtService;
import com.turkcell.library_cqrs_app.domain.entity.User;

@Component
public class LoginCommandHandler implements CommandHandler<LoginCommand, LoginResponse> {

    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginCommandHandler(
        UserBusinessRules userBusinessRules,
        PasswordEncoder passwordEncoder, 
        JwtService jwtService
    ) {
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse handle(LoginCommand command) {

        User user = userBusinessRules.getUserByEmailOrThrow(command.email());

        // todo: business rules sınıfına taşı
        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new BusinessException("Giriş bilgileri yanlış");
        }

        String jwt = jwtService.generateToken(user.getId(), command.email());

        return new LoginResponse(jwt);
    }

}
