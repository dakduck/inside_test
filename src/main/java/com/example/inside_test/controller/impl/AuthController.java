package com.example.inside_test.controller.impl;

import com.example.inside_test.controller.IAuthController;
import com.example.inside_test.model.dto.AuthCredentials;
import com.example.inside_test.model.dto.AuthResponseToken;
import com.example.inside_test.service.IAuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController implements IAuthController {

    private final IAuthenticationService IAuthenticationService;

    @PostMapping("login")
    @Override
    public AuthResponseToken login(@NonNull AuthCredentials authCredentials) {
        return new AuthResponseToken(IAuthenticationService.login(authCredentials.getName(), authCredentials.getPassword()));
    }
}
