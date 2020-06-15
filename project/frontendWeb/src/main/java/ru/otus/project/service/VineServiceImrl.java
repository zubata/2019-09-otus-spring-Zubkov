package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Vine;
import ru.otus.project.feign.FeignForVine;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VineServiceImrl implements VineService {

    private final FeignForVine feignVine;

    @Override
    public void insert(String vine) {
        feignVine.insertVine(vine);
    }

    @Override
    public List<Vine> getList() {
        return feignVine.getAllVines();
    }

    @Override
    public Page<Vine> getPageList(int id) {
        return feignVine.getVinesPage(id);
    }

    @Override
    public void deleteById(long id) {
        feignVine.deleteById(id);
    }

    @Override
    public void deleteAll() {
        feignVine.deleteAllVine();
    }

    @Override
    public Vine getById(long id) {
        return feignVine.getVineById(id);
    }

    @Override
    public List<Vine> getByName(String name) {
        return feignVine.getVineByName(name);
    }

    @Override
    public List<Vine> getByProducer(long id) {
        return feignVine.getVineByProducer(id);
    }
}
