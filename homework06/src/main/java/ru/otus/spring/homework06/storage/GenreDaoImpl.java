package ru.otus.spring.homework06.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework06.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(g) from Genre g");
        return (long) query.getSingleResult();
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public Genre getByName(String genrename) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.genreName = :genrename", Genre.class);
        query.setParameter("genrename", genrename);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAll() {
        Query query = em.createQuery("select g from Genre g");
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        Genre temp = em.find(Genre.class, id);
        em.remove(temp);
    }
}
