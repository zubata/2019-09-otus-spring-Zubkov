package ru.otus.spring.homework05.service;

import ru.otus.spring.homework05.config.IllegalText;

public interface BookService {
    String insert();

    void showAllRows();

    void showById();

    String delete();

    void showCount();

    String[] checkInputData(String book) throws IllegalText;
}
