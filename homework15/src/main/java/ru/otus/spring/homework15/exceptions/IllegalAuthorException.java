package ru.otus.spring.homework15.exceptions;

public class IllegalAuthorException extends RuntimeException {
    public IllegalAuthorException(String message) { super("Автор " + message); }

    public IllegalAuthorException(Long id) {
        super("Автор " + id + " не найден");
    }
}
