package ru.otus.project.service;

import ru.otus.project.domain.Vine;

import java.util.List;

public interface VineService {

    void insert ();

    List<Vine> getList();

    List<Vine> getListPage();

    void deleteById ();

    Vine getById();

    List<Vine> getByName();

    void deleteAll();

}
