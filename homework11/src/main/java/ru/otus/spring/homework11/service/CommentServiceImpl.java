package ru.otus.spring.homework11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Comment;
import ru.otus.spring.homework11.exceptions.IllegalCommentException;
import ru.otus.spring.homework11.storage.CommentDao;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public Mono<Comment> insert(Comment comment) { return commentDao.save(comment); }

    @Override
    public Flux<Comment> getByBook(String bookname) {
        return commentDao.getByBookName(bookname);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        if (commentDao.getById(id) == null) {
            throw new IllegalCommentException();
        }
        return commentDao.deleteById(id);
    }

}
