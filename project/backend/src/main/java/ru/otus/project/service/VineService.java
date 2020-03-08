package ru.otus.project.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.project.domain.Vine;

import java.util.List;

public interface VineService {

    Vine insert(String vine);

    List<Vine> getList();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(long id);

    Vine getById(long id);

    List<Vine> getByName(String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

}
