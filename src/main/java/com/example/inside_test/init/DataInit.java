package com.example.inside_test.init;

import com.example.inside_test.model.auth.Role;
import com.example.inside_test.model.auth.User;
import com.example.inside_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * Инициализация БД
 */
@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        // пароль - admin
        admin.setPassword("$2a$10$mYzYmDBVxfsUcAK9zUixE.0NLg9WWpPgLV5P1R.rU8gl2xnSEh5Ee");
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        admin.setRoles(new HashSet<>(List.of(role)));

        User user = new User();
        user.setUsername("user");
        // пароль - user
        user.setPassword("$2a$12$FGBJMNTgwuSGKJe9fvm47ePka5LzBOj3bPOgNpd7gfEhYiQoKZSbC");
        user.setRoles(new HashSet<>(List.of(role)));

        userRepository.saveAll(List.of(admin, user));
    }
}
