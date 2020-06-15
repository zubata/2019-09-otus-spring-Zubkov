package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Producer;
import ru.otus.project.feign.FeignForProducer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final FeignForProducer feignProducer;

    @Override
    public List<Producer> getList() {
        return feignProducer.getProducerList();
    }

    @Override
    public Producer getByName(String name) {
        return feignProducer.getProducerByName(name);
    }

    @Override
    public Producer getById(long id) {
        return feignProducer.getProducerById(id);
    }

    @Override
    public void deleteById(long id) {
        feignProducer.deleteById(id);
    }

    @Override
    public void deleteAll() {
        feignProducer.deleteAllProducer();
    }
}
