package ru.otus.spring.homework14.storage.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework14.domain.sql.AuthorSql;

public interface AuthorSqlDao extends JpaRepository<AuthorSql,Long> {
}
