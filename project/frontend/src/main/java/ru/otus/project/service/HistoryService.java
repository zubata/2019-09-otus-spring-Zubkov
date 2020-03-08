package ru.otus.project.service;

import ru.otus.project.domain.History;

import java.util.List;

public interface HistoryService {

    List<History> getVineHistoryById();

    List<History> getVineHistoryByName();

    void deleteVineHistoryById();

    void deleteAllHistory();

}
