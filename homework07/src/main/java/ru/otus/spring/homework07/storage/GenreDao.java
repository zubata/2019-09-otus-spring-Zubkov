package ru.otus.spring.homework07.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework07.domain.Genre;

public interface GenreDao extends JpaRepository<Genre,Long> {

    Genre getById(long id);

    Genre getBygenreName(String genrename);

}
