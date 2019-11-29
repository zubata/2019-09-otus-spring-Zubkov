package ru.otus.spring.homework12.exceptions;

public class IllegalBookException extends RuntimeException {

    public IllegalBookException(String message) {
        super("Книга " + message + " не найдена");
    }

    public IllegalBookException(Long id) {
        super("Книга " + id + " не найдена");
    }
}
