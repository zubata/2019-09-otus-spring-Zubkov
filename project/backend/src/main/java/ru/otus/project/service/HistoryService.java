package ru.otus.project.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.project.domain.History;

import java.util.List;

public interface HistoryService {

    List<History> getHistoryByVineId(long id);

    List<History> getHistoryByVineName(String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteByVineId(long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

}
