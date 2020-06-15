package ru.otus.project.service;

import ru.otus.project.domain.Producer;

import java.util.List;

public interface ProducerService {

    List<Producer> getList();

    Producer getByName(String name);

    Producer getById(long id);

    void deleteById(long id);

    void deleteAll();

}
