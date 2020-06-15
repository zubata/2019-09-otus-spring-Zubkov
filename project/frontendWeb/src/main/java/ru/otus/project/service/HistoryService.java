package ru.otus.project.service;

import ru.otus.project.domain.History;

import java.util.List;

public interface HistoryService {

    List<History> getVineHistoryById(long id);

    List<History> getVineHistoryByName(String name);

    void deleteVineHistoryById(long id);

    void deleteAllHistory();

}
