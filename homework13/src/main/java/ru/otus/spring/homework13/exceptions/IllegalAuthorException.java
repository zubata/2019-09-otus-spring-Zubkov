package ru.otus.spring.homework13.exceptions;

public class IllegalAuthorException extends RuntimeException {
    public IllegalAuthorException(String message) {
        super("Автор " + message + " не найден");
    }

    public IllegalAuthorException(Long id) {
        super("Автор " + id + " не найден");
    }
}
