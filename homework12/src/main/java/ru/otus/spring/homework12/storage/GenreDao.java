package ru.otus.spring.homework12.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework12.domain.Genre;

public interface GenreDao extends JpaRepository<Genre,Long> {

    Genre getById(long id);

    Genre getByName(String genrename);

}
