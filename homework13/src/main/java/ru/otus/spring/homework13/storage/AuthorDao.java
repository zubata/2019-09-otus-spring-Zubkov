package ru.otus.spring.homework13.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework13.domain.Author;

public interface AuthorDao extends JpaRepository<Author,Long> {

    Author getById(long id);

    Author getByName(String authorname);

}
