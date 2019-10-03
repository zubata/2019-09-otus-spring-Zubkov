package ru.otus.spring.homework06.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework06.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Comment comment) {
        em.persist(comment);
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(c) from Comment c");
        return (long) query.getSingleResult();
    }

    @Override
    public Comment getById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List getByBook(String bookname) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.bookName = :bookname", Comment.class);
        query.setParameter("bookname", bookname);
        return query.getResultList();
    }

    @Override
    public List getAll() {
        Query query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        Comment temp = getById(id);
        em.remove(temp);
    }
}
