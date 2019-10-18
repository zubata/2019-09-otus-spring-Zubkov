package ru.otus.spring.homework08.exceptions;

public class IllegalBookException extends RuntimeException {

    public IllegalBookException(String message) {
        super("Книга " + message + " не найдена");
    }
}
