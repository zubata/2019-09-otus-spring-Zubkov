package ru.otus.spring.homework13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework13.domain.Book;
import ru.otus.spring.homework13.domain.Comment;
import ru.otus.spring.homework13.dto.CommentDto;
import ru.otus.spring.homework13.exceptions.IllegalBookException;
import ru.otus.spring.homework13.exceptions.IllegalCommentException;
import ru.otus.spring.homework13.storage.BookDao;
import ru.otus.spring.homework13.storage.CommentDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    public String insert(CommentDto commentDto) {
        Comment temp = convertToDomain(commentDto);
        commentDao.save(temp);
        return String.format("Комментарий для книги %s успешно добавлен", temp.getBook().getName());
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
