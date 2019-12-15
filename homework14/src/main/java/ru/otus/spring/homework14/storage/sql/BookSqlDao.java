package ru.otus.spring.homework14.storage.sql;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework14.domain.sql.BookSql;

import java.util.List;

public interface BookSqlDao extends JpaRepository<BookSql,Long> {

    @EntityGraph(value = "BookWithAuthorAndGenre")
    List<BookSql> findAll();

}
