package ru.otus.spring.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework08.exceptions.IllegalBookException;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Comment;
import ru.otus.spring.homework08.storage.CommentDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
            commentDao.save(temp);
        } catch (IllegalBookException illegalBook) {
            illegalBook.printStackTrace();
            return null;
        }
        return bookname;
    }

    @Override
    public void showAllRows() {
        List<Comment> list = commentDao.findAll();
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public void showById() {
        ioService.output("Показать комментарий с id");
        String id = ioService.input();
        Comment temp = commentDao.getById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByBook() {
        ioService.output("Показать комменатрий для книги с названием");
        String bookname = ioService.input();
        List<Comment> list = commentDao.getByBookName(bookname);
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить комментарий по id");
        String id = ioService.input();
        commentDao.deleteById(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByBook() {
        ioService.output("Удалить все комментарии по книге");
        String bookname = ioService.input();
        try {
            checkBook(bookname);
            List<Comment> list = commentDao.getByBookName(bookname);
            list.forEach(temp -> commentDao.deleteById(temp.getId()));
        } catch (IllegalBookException illegalBook) {
            illegalBook.printStackTrace();
            return null;
        }
        return bookname;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(commentDao.count()));
    }

    private Book checkBook(String bookname) throws IllegalBookException {
        Book temp = bookService.getBook(bookname);
        if (temp == null) throw new IllegalBookException(bookname);
        return temp;
    }

}
