package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.History;
import ru.otus.project.feign.FeignForHistory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final FeignForHistory feignHistory;

    @Override
    public List<History> getVineHistoryById(long id) {
        return feignHistory.getVineHistoryById(id);
    }

    @Override
    public List<History> getVineHistoryByName(String name) {
        return feignHistory.getVineHistoryByName(name);
    }

    @Override
    public void deleteVineHistoryById(long id) {
        feignHistory.deleteVineById(id);
    }

    @Override
    public void deleteAllHistory() {
        feignHistory.deleteAllHistory();
    }


}
