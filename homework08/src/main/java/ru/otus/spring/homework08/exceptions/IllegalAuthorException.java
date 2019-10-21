package ru.otus.spring.homework08.exceptions;

public class IllegalAuthorException extends RuntimeException {
    public IllegalAuthorException(String message) {
        super("Автор " + message + " не найден");
    }
}
