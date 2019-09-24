package ru.otus.spring.homework05.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework05.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations njdbc;

    @Override
    public void insert(Book book) {
        long id = this.count() + 1;
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id).
                addValue("bookname", book.getBookName()).
                addValue("authorname", book.getAuthorName());
        njdbc.update("insert into books (id,`bookname`,`authorname`) values (:id,:bookname,:authorname)", params);
    }

    @Override
    public int count() {
        JdbcOperations jdbc = njdbc.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return njdbc.queryForObject("select * from books where id = :id", param, new BookMapper());
    }

    @Override
    public List getAll() {
        return njdbc.query("select * from books", new BookMapper());
    }

    @Override
    public void delete(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        njdbc.update("delete from books where id = :id", param);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String bookname = resultSet.getString("bookname");
            String authorname = resultSet.getString("authorname");
            return new Book(id, bookname, authorname);
        }
    }
}
