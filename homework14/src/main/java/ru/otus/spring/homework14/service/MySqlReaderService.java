package ru.otus.spring.homework14.service;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class MySqlReaderService<T> implements ItemReader<T> {

    private int count;
    private List<T> listObjects;

    public void setJpaRepo(JpaRepository jpaRepo) {
        listObjects = jpaRepo.findAll();
        count = 0;
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        T nextObject = null;
        if (count < listObjects.size()) {
            nextObject = listObjects.get(count);
            count++;
        }
        return nextObject;
    }
}
