package ru.otus.spring.homework05.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations njdbc;

    @Override
    public void insert(Author author) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("authorname", author.getAuthorName());
        njdbc.update("insert into authors (`authorname`) values (:authorname)", params);
    }

    @Override
    public int count() {
        JdbcOperations jdbc = njdbc.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return njdbc.queryForObject("select * from authors where id = :id", param, new AuthorMapper());
    }

    @Override
    public Author getByName(String authorname) {
        Map<String, Object> param = Collections.singletonMap("authorname", authorname);
        return njdbc.queryForObject("select * from authors where authorname = :authorname", param, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return njdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        njdbc.update("delete from authors where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String authorname = resultSet.getString("authorname");
            return new Author(id, authorname);
        }
    }
}
