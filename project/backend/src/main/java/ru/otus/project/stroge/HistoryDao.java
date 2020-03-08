package ru.otus.project.stroge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.domain.History;

import java.util.List;

public interface HistoryDao extends JpaRepository<History,Long> {

    List<History> findByVineName(String vineName);

    List<History> findByVineId(long id);

    @Transactional
    void deleteByVineId(long id);

}
