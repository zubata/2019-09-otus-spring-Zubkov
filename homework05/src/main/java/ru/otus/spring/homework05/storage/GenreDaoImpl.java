package ru.otus.spring.homework05.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations njdbc;

    @Override
    public void insert(Genre genre) {
        long id = this.count() + 1;
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id).
                addValue("genrename", genre.getGenreName()).
                addValue("bookname", genre.getBookName());
        njdbc.update("insert into genres (id,`genrename`,`bookname`) values (:id,:genrename,:bookname)", params);
    }

    @Override
    public int count() {
        JdbcOperations jdbc = njdbc.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return njdbc.queryForObject("select * from genres where id = :id", param, new GenreMapper());
    }

    @Override
    public List getAll() {
        return njdbc.query("select * from genres", new GenreMapper());
    }

    @Override
    public void delete(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        njdbc.update("delete from genres where id = :id", param);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String genrename = resultSet.getString("genrename");
            String bookname = resultSet.getString("bookname");
            return new Genre(id, genrename, bookname);
        }
    }
}
