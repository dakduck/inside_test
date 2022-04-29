package com.example.inside_test.service.impl;

import com.example.inside_test.service.IAuthenticationService;
import com.example.inside_test.service.IJWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService implements IAuthenticationService {
    private static final String TOKEN_PREFIX = "Bearer_";

    private final AuthenticationManager authenticationManager;
    private final IJWTService IJWTService;

    @Override
    public String login(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            log.info("login: Access is allowed. Generate token for user {}", username);
        } catch (BadCredentialsException e) {
            log.warn("login: Incorrect username or password. Access is denied.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password.");
        }
        return TOKEN_PREFIX + IJWTService.generateToken(((UserDetails) authentication.getPrincipal()));
    }
}
