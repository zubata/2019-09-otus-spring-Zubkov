package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Producer;
import ru.otus.project.exception.NotFoundEntity;
import ru.otus.project.stroge.ProducerDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final ProducerDao producerDao;

    @Override
    public List<Producer> getList() {
        return producerDao.findAll();
    }

    @Override
    public void deleteById(long id) {
        try {
            producerDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundEntity("Производитель", "id");
        }
    }

    @Override
    public Producer getById(long id) {
        Producer temp = producerDao.findById(id).get();
        if (temp == null) throw new NotFoundEntity("Производитель", "id");
        return temp;
    }

    @Override
    public Producer getByName(String name) {
        Producer temp = producerDao.findByName(name);
        if (temp == null) throw new NotFoundEntity("Производитель", "именованием");
        return temp;
    }

    @Override
    public void deleteAll() {
        producerDao.deleteAllInBatch();
    }
}
