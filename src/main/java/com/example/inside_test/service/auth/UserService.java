package com.example.inside_test.service.auth;

import com.example.inside_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Сервис для получения пользователя, реализовывает интерфейс {@link org.springframework.security.core.userdetails.UserDetailsService}
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Метод получения пользователя по имени пользователя
     * @param username Имя пользователя
     * @return Объект {@link org.springframework.security.core.userdetails.UserDetails}, содержащий основную информацию пользователя
     * @throws UsernameNotFoundException если пользователь с таким именем в БД не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException(
                    String.format("User with username '%s' not found.", username)
            );
        });
    }
}
