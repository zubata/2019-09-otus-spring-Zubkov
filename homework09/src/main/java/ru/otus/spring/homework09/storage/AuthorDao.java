package ru.otus.spring.homework09.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework09.domain.Author;

public interface AuthorDao extends JpaRepository<Author,Long> {

    Author getById(long id);

    Author getByName(String authorname);

}
