package ru.otus.spring.homework07.config;

public class IllegalBookException extends RuntimeException {

    public IllegalBookException(String message) {
        super("Книга " + message + " не найдена");
    }
}
