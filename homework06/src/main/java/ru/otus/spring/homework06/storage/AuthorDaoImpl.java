package ru.otus.spring.homework06.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework06.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(a) from Author a");
        return (long) query.getSingleResult();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public Author getByName(String authorname) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.authorName = :authorname", Author.class);
        query.setParameter("authorname", authorname);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        Query query = em.createQuery("select a from Author a");
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        Author temp = getById(id);
        em.remove(temp);
    }
}
