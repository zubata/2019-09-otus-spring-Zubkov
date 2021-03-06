package ru.otus.spring.homework17.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework17.domain.Book;

@Repository
public interface RestControllerForBook extends PagingAndSortingRepository<Book, Long> {
}
