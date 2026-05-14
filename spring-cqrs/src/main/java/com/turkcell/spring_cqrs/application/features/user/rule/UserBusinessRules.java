package com.turkcell.spring_cqrs.application.features.user.rule;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.turkcell.spring_cqrs.core.exception.AlreadyExistsException;
import com.turkcell.spring_cqrs.core.exception.UnauthenticatedException;
import com.turkcell.spring_cqrs.domain.User;
import com.turkcell.spring_cqrs.persistence.repository.UserRepository;

@Component
public class UserBusinessRules {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserBusinessRules(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder    
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                    .orElseThrow(() -> new UnauthenticatedException("Giriş bilgileri hatalı."));
    }

    public void passwordMustMatch(String password, User user) {
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthenticatedException("Giriş bilgileri hatalı.");
        }
    }

    public void userWithSameEmailMustNotExist(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new AlreadyExistsException("Bu e-posta adresi zaten kullanımda.");
        }
    }

}
