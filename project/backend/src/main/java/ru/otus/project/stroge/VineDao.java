package ru.otus.project.stroge;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.domain.Vine;

import java.time.LocalDate;
import java.util.List;

public interface VineDao extends JpaRepository<Vine, Long> {

    @EntityGraph(value = "VineAndProducer")
    List<Vine> findAll();

    @EntityGraph(value = "VineAndProducer")
    Vine findById(long id);

    @EntityGraph(value = "VineAndProducer")
    List<Vine> findByName(String name);

    //@Modifying
    //@Transactional
    //@Query(value = "update vines as v set is_available=false from history h where h.date != ?1 and v.id=h.vine_id",
    //nativeQuery = true)
    @Query("update Vine v set is_available=false")
    @Modifying
    @Transactional
    int updateAllVinesToUnavailableStatus();
}
