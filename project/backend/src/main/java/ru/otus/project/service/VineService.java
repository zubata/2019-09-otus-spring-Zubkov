package ru.otus.project.service;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.project.domain.Vine;

import java.util.List;

public interface VineService {

    Vine insert(String vine);

    List<Vine> getList();

    Page<Vine> getListPage(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(long id);

    Vine getById(long id);

    List<Vine> getByName(String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<Vine> getByProducerId(long id);

}
