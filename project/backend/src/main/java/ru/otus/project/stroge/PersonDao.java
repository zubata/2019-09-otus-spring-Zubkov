package ru.otus.project.stroge;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.domain.Person;

import java.util.Optional;

public interface PersonDao extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String name);

    @EntityGraph(value = "PersonsAndVine", attributePaths = "vine.producer")
    Optional<Person> getByUsername(String name);

    @Query(value = "delete from favourite_vine where vine_id=?1", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteVineFromFavourite(long id);

    @Query(value = "delete from favourite_vine", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteAllVinesFromFavourite();

}
