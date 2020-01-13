package ru.otus.spring.homework16.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework16.domain.Person;

import java.util.Optional;

public interface PersonDao extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);

}
