package ru.otus.project.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Код ошибки 401 \nТакого пользователя нет, проверьте логин/пароль");
    }
}
