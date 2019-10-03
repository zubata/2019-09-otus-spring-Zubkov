package ru.otus.spring.homework06.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework06.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Book book) {
        em.merge(book);
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(b) from Book b");
        return (long) query.getSingleResult();
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book getByName(String bookname) {
        TypedQuery<Book> query = em.createQuery("select a from Book a where a.bookName = :bookname", Book.class);
        query.setParameter("bookname", bookname);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        Query query = em.createQuery("select b from Book b");
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        Book temp = em.find(Book.class, id);
        em.remove(temp);
    }


}
