package com.example.inside_test.service;

/**
 * Сервис для аутентификации пользователей
 */
public interface IAuthenticationService {
    /**
     * Метод входа в приложение
     * @param username Полученное от пользователя имя
     * @param password Полученный от пользователя пароль
     * @return Строка, содержащая токен авторизованного пользователя
     */
    String login(String username, String password);
}
