package com.example.inside_test.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Сервис для работы с JSON Web Token
 */
public interface IJWTService {

    /**
     * Метод создает Токен
     * @param userDetails Основная информация о пользователе
     * @return Строка, содержащая токен авторизованного пользователя
     */
    String generateToken(UserDetails userDetails);

    /**
     * Метод извлечения из полученного в запросе токена имя пользователя
     * @param authorizationHeader Строка, содержащая заголовок авторизации
     * @return Имя авторизованного пользователя
     */
    String extractUsername(String authorizationHeader);

    /**
     * Метод извлечения из полученного в запросе токена привилегий пользователя
     * @param authorizationHeader Строка, содержащая заголовок авторизации
     * @return Привилегии пользователя, перечисленные через запятую
     */
    String extractAuthorities(String authorizationHeader);
}
