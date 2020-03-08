package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.config.IOService;
import ru.otus.project.domain.Vine;
import ru.otus.project.rest.FeignForVine;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VineServiceImrl implements VineService {

    private final FeignForVine vineDao;
    private final IOService ioService;

    @Override
    public void insert() {
        ioService.output("Введите данные вина через запятую");
        String mas = ioService.input();
        vineDao.insertVine(mas);
    }

    @Override
    public List<Vine> getList() {
        List<Vine> list = vineDao.getAllVines();
        list.forEach(i -> ioService.output(i.toString()));
        return list;
    }

    @Override
    public void deleteById() {
        ioService.output("Введите id, который надо удалить");
        long id = Long.parseLong(ioService.input());
        vineDao.deleteById(id);
        ioService.output(String.format("Вино с id %d удаленно", id));
    }

    @Override
    public void deleteAll() {
        vineDao.deleteAllVine();
        ioService.output("Все вина удалены");
    }

    @Override
    public Vine getById() {
        ioService.output("Введите id вина");
        long id = Long.parseLong(ioService.input());
        Vine vine = vineDao.getVineById(id);
        if (vine == null) ioService.output("Данного id нет в базе данных");
        ioService.output(vine.toString());
        return vine;
    }

    @Override
    public List<Vine> getByName() {
        ioService.output("Введите именование вина");
        String name = ioService.input();
        List<Vine> vines = vineDao.getVineByName(name);
        if (vines.size() ==0) ioService.output("Данного названия нет в базе данных");
        vines.forEach(v->ioService.output(v.toString()));
        return vines;
    }
}
