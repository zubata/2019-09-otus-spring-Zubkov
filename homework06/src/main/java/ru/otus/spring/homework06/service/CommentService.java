package ru.otus.spring.homework06.service;

public interface CommentService {

    String insert();

    void showAllRows();

    void showById();

    void showByName();

    String deleteById();

    String deleteByBook();

    void showCount();

}
