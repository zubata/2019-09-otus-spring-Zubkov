package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.config.IOService;
import ru.otus.project.domain.Producer;
import ru.otus.project.rest.FeignForProducer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final FeignForProducer feignProducer;
    private final IOService ioService;

    @Override
    public List<Producer> getList() {
        List<Producer> list = feignProducer.getProducerList();
        ioService.output("Список производителей:");
        list.forEach(i -> ioService.output(i.toString()));
        return list;
    }

    @Override
    public Producer getByName() {
        ioService.output("Введите наименование производителя");
        String name = ioService.input();
        Producer producer = feignProducer.getProducerByName(name);
        if (producer == null) ioService.output("Данного названия нет в базе данных");
        ioService.output(producer.toString());
        return producer;
    }

    @Override
    public Producer getById() {
        ioService.output("Введите id производителя");
        long id = Long.parseLong(ioService.input());
        Producer producer = feignProducer.getProducerById(id);
        if (producer == null) ioService.output("Данного id нет в базе данных");
        ioService.output(producer.toString());
        return producer;
    }

    @Override
    public void deleteById() {
        ioService.output("Введите id, который надо удалить");
        long id = Long.parseLong(ioService.input());
        feignProducer.deleteById(id);
        ioService.output(String.format("Производитель с id %d и его вина удаленны", id));
    }

    @Override
    public void deleteAll() {
        feignProducer.deleteAllProducer();
        ioService.output("Все производители и их вина удалены");
    }
}
