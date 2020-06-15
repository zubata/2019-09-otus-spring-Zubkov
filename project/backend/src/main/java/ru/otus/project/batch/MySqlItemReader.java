package ru.otus.project.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MySqlItemReader<T> implements ItemReader<T> {

    private List<T> listObjects;
    private int count;

    public void setJpaRepo(JpaRepository jpaRepo) {
        listObjects = jpaRepo.findAll();
        count = 0;
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        T nextObject = null;
        if (count < listObjects.size()) {
            nextObject = listObjects.get(count);
            count ++;
        }
        return nextObject;
    }
}
