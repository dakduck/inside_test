package com.example.inside_test.controller;

import com.example.inside_test.model.dto.AuthCredentials;
import com.example.inside_test.model.dto.AuthResponseToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Контроллер для работы с аутентификацией пользователей
 */
@Tag(
        name = "Аутентификация",
        description = "Контроллер для аутентификации пользователей"
)
public interface IAuthController {

    /**
     * Метод для входа в приложение
     * @param authCredentials Экземпляр класса {@link com.example.inside_test.model.dto.AuthCredentials}, содержащий имя и пароль пользователя
     * @return Объект класса {@link com.example.inside_test.model.dto.AuthResponseToken}, содержащий токен авторизованного пользователя
     */
    @Operation(
            summary = "Аутентификация",
            description = "Аутентификация пользователя через пару логин-пароль",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно авторизован"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Объект с именем пользователя и паролем",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            summary = "Объект с авторизационными данными.",
                                            value = "{\"name\": \"user\",\n\"password\": \"user\"}"
                                    )
                            }
                    )
            )
    )
    AuthResponseToken login(@NonNull @RequestBody AuthCredentials authCredentials);
}
