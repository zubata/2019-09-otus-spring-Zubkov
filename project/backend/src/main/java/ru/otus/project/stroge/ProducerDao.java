package ru.otus.project.stroge;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.domain.Producer;

public interface ProducerDao extends JpaRepository<Producer, Long> {

    Producer findByName(String name);

}
