package ru.otus.project.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.project.domain.Producer;
import ru.otus.project.domain.Vine;

import java.util.List;

public interface ProducerService {

    List<Producer> getList();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById (long id);

    Producer getById(long id);

    Producer getByName(String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();
}
