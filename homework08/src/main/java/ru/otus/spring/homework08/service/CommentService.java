package ru.otus.spring.homework08.service;

public interface CommentService {

    String insert();

    void showAllRows();

    void showById();

    void showByBook();

    String deleteById();

    String deleteByBook();

    void showCount();

}
