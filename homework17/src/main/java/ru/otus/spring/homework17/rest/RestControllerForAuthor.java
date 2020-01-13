package ru.otus.spring.homework17.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework17.domain.Author;

@Repository
public interface RestControllerForAuthor extends PagingAndSortingRepository<Author, Long> {

}
