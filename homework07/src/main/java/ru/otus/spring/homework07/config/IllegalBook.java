package ru.otus.spring.homework07.config;

public class IllegalBook extends RuntimeException {

    public IllegalBook(String message) {
        super("Книга " + message + " не найдена");
    }
}
