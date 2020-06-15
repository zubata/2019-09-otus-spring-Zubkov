package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Vine;
import ru.otus.project.feign.FeignForAuth;
import ru.otus.project.feign.FeignForPerson;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final FeignForPerson feignForPerson;

    @Override
    public void addFavouriteVine(long id) { feignForPerson.addFavouriteVine(id); }

    @Override
    public void deleteFavouriteVine(long id) {
        feignForPerson.deleteFavouriteVine(id);
    }

    @Override
    public void deleteAllFavouriteVine() {
        feignForPerson.deleteAllFavouriteVine();
    }

    @Override
    public List<Vine> getFavouriteVines() { return feignForPerson.getFavouriteVines(); }
}
