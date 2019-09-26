package ru.otus.spring.homework05.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework05.domain.Author;
import ru.otus.spring.homework05.domain.Book;
import ru.otus.spring.homework05.domain.Genre;

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
        Long authorId = book.getAuthor().getId();
        Long genreId = book.getGenre().getId();
        SqlParameterSource params = new MapSqlParameterSource().
                addValue("bookname", book.getBookName()).
                addValue("author_id", authorId).
                addValue("genre_id", genreId);
        njdbc.update("insert into books (`bookname`,`author_id`,`genre_id`) values (:bookname,:author_id,:genre_id)", params);
    }

    @Override
    public int count() {
        JdbcOperations jdbc = njdbc.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return njdbc.queryForObject("select b.id, b.bookname, b.author_id, a.authorname, b.genre_id, g.genrename " +
                        "from (books b inner join genres g on b.genre_id=g.id) " +
                        "inner join authors a on b.author_id=a.id where b.id=:id",
                param, new BookMapper());
    }

    @Override
    public List getAll() {
        return njdbc.query("select b.id, b.bookname, b.author_id, a.authorname, b.genre_id, g.genrename " +
                "from (books b inner join genres g on b.genre_id=g.id) " +
                "inner join authors a on b.author_id=a.id", new BookMapper());
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
            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("authorname"));
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("genrename"));
            return new Book(id, bookname, author, genre);
        }
    }

}
