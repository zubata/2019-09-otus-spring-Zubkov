package ru.otus.project.service;

import ru.otus.project.domain.Vine;

import java.util.List;

public interface PersonService {

    void addFavouriteVine(long id);

    void deleteFavouriteVine(long id);

    void deleteAllFavouriteVine();

    List<Vine> getFavouriteVines();

}
