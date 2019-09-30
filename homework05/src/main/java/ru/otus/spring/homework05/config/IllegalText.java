package ru.otus.spring.homework05.config;

public class IllegalText extends Exception {

    public IllegalText(String message) {
        super("Неправильно введены данные книги " + message + ": число слов (данных) через точку запятой неравно трём");
    }
}
