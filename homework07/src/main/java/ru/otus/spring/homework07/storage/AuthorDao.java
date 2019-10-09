package ru.otus.spring.homework07.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework07.domain.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author,Long> {

    Author findById(long id);

    Author findByauthorName(String authorname);

}
