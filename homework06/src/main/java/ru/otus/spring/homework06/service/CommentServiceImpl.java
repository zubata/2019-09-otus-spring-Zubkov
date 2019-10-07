package ru.otus.spring.homework06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework06.config.IllegalBook;
import ru.otus.spring.homework06.domain.Book;
import ru.otus.spring.homework06.domain.Comment;
import ru.otus.spring.homework06.storage.CommentDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final IOService ioService;
    private final BookService bookService;

    @Override
    public String insert() {
        ioService.output("Укажите книгу, для который хотите написать комменатрий");
        String bookname = ioService.input();
        try {
            Comment temp = new Comment(checkBook(bookname));
            ioService.output("Напишите ваш комментарий");
            String strComment = ioService.input();
            temp.setComment(strComment);
            commentDao.insert(temp);
        } catch (IllegalBook illegalBook) {
            illegalBook.printStackTrace();
            return null;
        }
        return bookname;
    }

    @Override
    public void showAllRows() {
        List<Comment> list = commentDao.getAll();
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public void showById() {
        ioService.output("Показать комментарий с id");
        long id = Long.parseLong(ioService.input());
        Comment temp = commentDao.getById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByName() {
        ioService.output("Показать комменатрий для книги с названием");
        String bookname = ioService.input();
        List<Comment> list = commentDao.getByBook(bookname);
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить комментарий по id");
        long id = Long.parseLong(ioService.input());
        commentDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByBook() {
        ioService.output("Удалить все комментарии по книге");
        String bookname = ioService.input();
        try {
            checkBook(bookname);
            List<Comment> list = commentDao.getByBook(bookname);
            list.forEach(temp -> commentDao.delete(temp.getId()));
        } catch (IllegalBook illegalBook) {
            illegalBook.printStackTrace();
            return null;
        }
        return bookname;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(commentDao.count()));
    }

    private Book checkBook(String bookname) throws IllegalBook {
        try {
            return bookService.getBook(bookname);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalBook(bookname);
        }
    }

}
