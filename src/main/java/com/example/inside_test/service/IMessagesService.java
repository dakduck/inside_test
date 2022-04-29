package com.example.inside_test.service;

import com.example.inside_test.model.dto.CreatedMessages;
import com.example.inside_test.model.dto.NewMessage;
import lombok.NonNull;

import java.security.Principal;

/**
 * Сервис для работы с {@link com.example.inside_test.model.Message}.
 * Позволяет добавлять и получать из БД сообщения авторизованного пользователя.
 */
public interface IMessagesService {

    /**
     * Метод для добавления сообщений в БД.
     * @param newMessage сообщение, содержащее имя пользователя и текст
     */
    void add(@NonNull NewMessage newMessage);

    /**
     * Получить последние N сообщений из БД для указанного пользователя.
     * @param count количество сообщений, которое необходимо получить
     * @param principal авторизованный пользователь
     * @return экземпляр класса {@link com.example.inside_test.model.dto.CreatedMessages}, содержащий список последних сообщений
     * @throws Exception в случае отсутствия сообщений в БД у заданного пользователя
     */
    CreatedMessages getLastCountMessages(@NonNull int count, @NonNull Principal principal) throws Exception;
}
