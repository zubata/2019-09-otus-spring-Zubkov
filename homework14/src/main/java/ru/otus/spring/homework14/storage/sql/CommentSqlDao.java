package ru.otus.spring.homework14.storage.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework14.domain.sql.CommentSql;

public interface CommentSqlDao extends JpaRepository<CommentSql,Long> {
}
