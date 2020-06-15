package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.matcher.MethodSortMatcher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Producer;
import ru.otus.project.domain.Vine;
import ru.otus.project.exception.NotFoundEntity;
import ru.otus.project.stroge.PersonDao;
import ru.otus.project.stroge.ProducerDao;
import ru.otus.project.stroge.VineDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VineServiceImrl implements VineService {

    private final VineDao vineDao;
    private final ProducerDao producerDao;
    private final PersonDao personDao;

    @Override
    public Vine insert(String vine) {
        /*String[] mas = vine.split(",");
        Vine temp = new Vine();
        temp.setId(Long.parseLong(mas[0]));
        temp.setYear(mas[1]);
        temp.setType(mas[2]);
        temp.setColor(mas[3]);
        temp.setCapacity(mas[4]);
        temp.setCountry(mas[5]);
        temp.setCost(Double.parseDouble(mas[6]));
        Producer tempProd = producerDao.findByName(mas[7]);
        if (tempProd == null) tempProd = producerDao.save(new Producer(mas[7]));
        temp.setProducer(tempProd);
        temp.setName(mas[8]);
        temp.setUrl(mas[9]);
        return vineDao.save(temp);*/
        return new Vine();
    }

    @Override
    public List<Vine> getList() {
        return vineDao.findAll();
    }

    @Override
    public Page<Vine> getListPage(int id) {
        return vineDao.findAll(PageRequest.of(id, 6));
    }

    @Override
    public void deleteById(long id) {
        personDao.deleteVineFromFavourite(id);
        try {
            vineDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundEntity("Вино", "id");
        }
    }

    @Override
    public void deleteAll() {
        personDao.deleteAllVinesFromFavourite();
        vineDao.deleteAllInBatch();
    }

    @Override
    public Vine getById(long id) {
        Vine temp = vineDao.findById(id);
        if (temp == null) throw new NotFoundEntity("Вино", "id");
        return temp;
    }

    @Override
    public List<Vine> getByName(String name) {
        List<Vine> temp = vineDao.findByName(name);
        if (temp.size() == 0) throw new NotFoundEntity("Вино", "наименованием");
        return temp;
    }

    @Override
    public List<Vine> getByProducerId(long id) {
        List<Vine> temp = vineDao.findByProducerId(id);
        if (temp.size() == 0) throw new NotFoundEntity("Вин", " с таким производителем не найдено");
        return temp;
    }
}
