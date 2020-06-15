package ru.otus.project.service;

import org.springframework.data.domain.Page;
import ru.otus.project.domain.Vine;

import java.util.List;

public interface VineService {

    void insert (String name);

    List<Vine> getList();

    Page<Vine> getPageList(int id);

    void deleteById (long id);

    Vine getById(long id);

    List<Vine> getByName(String name);

    List<Vine> getByProducer(long id);

    void deleteAll();

}
