package ru.otus.spring.homework06.config;

public class IllegalText extends RuntimeException {

    public IllegalText(String message) {
        super("Неправильно введены данные книги " + message + ": число слов (данных) через точку запятой неравно трём");
    }
}
