package com.example.inside_test.controller;

import com.example.inside_test.model.dto.CreatedMessages;
import com.example.inside_test.model.dto.NewMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Контроллер для работы с запросами о сообщениях
 */
@Tag(
        name = "Сообщения",
        description = "Контроллер для работы с сообщениями"
)
public interface IMessageController {

    /**
     * Метод добавления нового сообщения
     * @param newMessage экземпляр класса {@link com.example.inside_test.model.dto.NewMessage}, содержащий имя пользователя и текст нового сообщения
     */
    @Operation(
            summary = "Добавить сообщение",
            description = "Добавить сообщение указанному пользователю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение успешно ассоциировано с пользователем и сохранено в БД"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещён, пользователь не авторизован"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Объект с именем пользователя и сообщением",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            summary = "Объект входящего сообщения",
                                            value = "{\"name\": \"user\",\n\"message\": \"new message\"}"
                                    )
                            }
                    )
            )
    )
    void addMessage(@NonNull @RequestBody NewMessage newMessage);

    /**
     * Метод получения N последних сообщений пользователя
     * @param count Количество сообщений
     * @param principal Авторизованный пользователь
     * @return экземпляр класса {@link com.example.inside_test.model.dto.CreatedMessages}, содержащий список последних сообщений
     * @throws Exception в случае отсутствия сообщений в БД у заданного пользователя
     */
    @Operation(
            summary = "Получить последние сообщения пользователя",
            description = "Получить N последних сообщений авторизованного пользователя",
            parameters = {
                    @Parameter(
                            name = "count",
                            description = "Количество сообщений пользователя, которые необходимо вернуть"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Операция успешно выполнена.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreatedMessages.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещён, пользователь не авторизован.",
                            content = @Content()
                    )
            }
    )
    CreatedMessages getLastCountMessages(@NonNull @RequestParam(name = "count") int count, @NonNull Principal principal) throws Exception;
}
