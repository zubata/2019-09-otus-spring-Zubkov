package ru.otus.project.service;

import ru.otus.project.domain.Producer;

import java.util.List;

public interface ProducerService {

    List<Producer> getList();

    Producer getByName();

    Producer getById();

    void deleteById();

    void deleteAll();

}
