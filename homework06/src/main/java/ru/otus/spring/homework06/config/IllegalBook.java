package ru.otus.spring.homework06.config;

public class IllegalBook extends RuntimeException {

    public IllegalBook(String message) {
        super("Книга " + message + " не найдена");
    }
}
