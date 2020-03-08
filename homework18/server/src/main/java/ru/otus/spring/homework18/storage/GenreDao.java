package ru.otus.spring.homework18.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework18.domain.Genre;

public interface GenreDao extends JpaRepository<Genre,Long> {

    Genre getByName(String genrename);

}
