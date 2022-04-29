package com.example.inside_test.config.beans;

import com.example.inside_test.model.auth.User;
import com.example.inside_test.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomAuditorAware implements AuditorAware<User> {
    private final UserService userService;

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of(
                ((User) userService.loadUserByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ))
        );
    }
}
