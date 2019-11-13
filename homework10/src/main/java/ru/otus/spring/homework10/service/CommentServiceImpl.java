package ru.otus.spring.homework10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework10.domain.Book;
import ru.otus.spring.homework10.domain.Comment;
import ru.otus.spring.homework10.dto.CommentDto;
import ru.otus.spring.homework10.exceptions.IllegalBookException;
import ru.otus.spring.homework10.exceptions.IllegalCommentException;
import ru.otus.spring.homework10.storage.BookDao;
import ru.otus.spring.homework10.storage.CommentDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    public Comment insert(CommentDto commentDto) {
        Comment temp = convertToDomain(commentDto);
        return commentDao.save(temp);
    }


    @Override
    public List<Comment> showByBook(String bookname) { return commentDao.getByBookName(bookname);}

    @Override
    public void deleteById(long id) {
        if (commentDao.getById(id) == null) {
            throw new IllegalCommentException();
        }
        commentDao.deleteById(id);
    }

    private Book getBook(String bookname) throws IllegalBookException {
        Book temp = bookDao.getByName(bookname);
        if (temp == null) throw new IllegalBookException(bookname);
        return temp;
    }

    private Comment convertToDomain(CommentDto commentDto) {
        String bookname = commentDto.getBookname();
        Comment temp = new Comment(getBook(bookname));
        temp.setComment(commentDto.getComment());
        return temp;
    }

}
