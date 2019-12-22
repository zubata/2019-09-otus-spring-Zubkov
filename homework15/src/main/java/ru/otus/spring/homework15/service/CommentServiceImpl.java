package ru.otus.spring.homework15.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework15.domain.Book;
import ru.otus.spring.homework15.domain.Comment;
import ru.otus.spring.homework15.dto.CommentDto;
import ru.otus.spring.homework15.exceptions.IllegalBookException;
import ru.otus.spring.homework15.exceptions.IllegalCommentException;
import ru.otus.spring.homework15.storage.BookDao;
import ru.otus.spring.homework15.storage.CommentDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    public Comment insert(Comment comment) {
        return commentDao.save(comment);
    }


    @Override
    public List<Comment> showByBook(String bookname) { return commentDao.getByBookName(bookname);}

    @Override
    public String deleteById(long id) {
        if (commentDao.getById(id) == null) {
            throw new IllegalCommentException();
        }
        commentDao.deleteById(id);
        return "Комментарий удалён";
    }

    private Book getBook(String bookname) throws IllegalBookException {
        Book temp = bookDao.getByName(bookname);
        if (temp == null) throw new IllegalBookException();
        return temp;
    }

    public Comment convertToDomain(CommentDto commentDto) {
        String bookname = commentDto.getBookname();
        Comment temp = new Comment(getBook(bookname));
        temp.setComment(commentDto.getComment());
        return temp;
    }

}
