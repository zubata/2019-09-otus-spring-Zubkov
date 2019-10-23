package ru.otus.spring.homework08.exceptions;

public class IllegalTextException extends RuntimeException {

    public IllegalTextException(String message) {
        super("Неправильно введены данные книги " + message + ": число слов (данных) через точку запятой неравно трём");
    }
}
