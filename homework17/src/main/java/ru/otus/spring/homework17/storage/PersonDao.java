package ru.otus.spring.homework17.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework17.domain.Person;

import java.util.Optional;

public interface PersonDao extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);

}
