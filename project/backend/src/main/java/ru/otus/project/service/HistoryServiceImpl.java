package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.History;
import ru.otus.project.domain.Vine;
import ru.otus.project.exception.NotFoundEntity;
import ru.otus.project.stroge.HistoryDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryDao historyDao;

    @Override
    public List<History> getHistoryByVineId(long id) {
        List<History> temp = historyDao.findByVineId(id);
        if (temp == null) throw new NotFoundEntity("Истории цен", "id");
        return temp;
    }

    @Override
    public List<History> getHistoryByVineName(String name) {
        List<History> temp = historyDao.findByVineName(name);
        if (temp == null) throw new NotFoundEntity("Истории цен", "названием вина");
        return temp;
    }

    //Лишний запрос, тк custom delete возвращает ничего и если не истории, то не вылетает исключения
    @Override
    public void deleteByVineId(long id) {
        List<History> temp = historyDao.findByVineId(id);
        if (temp.size() == 0) throw new NotFoundEntity("Истории цен", "id");
        historyDao.deleteByVineId(id);
    }

    @Override
    public void deleteAll() { historyDao.deleteAll();}
}
