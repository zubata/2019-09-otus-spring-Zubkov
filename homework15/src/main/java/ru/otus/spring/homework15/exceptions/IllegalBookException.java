package ru.otus.spring.homework15.exceptions;

public class IllegalBookException extends RuntimeException {

    public IllegalBookException() {
        super("Книга не найдена");
    }

    public IllegalBookException(Long id) {
        super("Книга " + id + " не найдена");
    }
}
