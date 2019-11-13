package ru.otus.spring.homework10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework10.domain.Author;
import ru.otus.spring.homework10.domain.Book;
import ru.otus.spring.homework10.domain.Genre;
import ru.otus.spring.homework10.dto.BookDto;
import ru.otus.spring.homework10.exceptions.IllegalBookException;
import ru.otus.spring.homework10.storage.AuthorDao;
import ru.otus.spring.homework10.storage.BookDao;
import ru.otus.spring.homework10.storage.GenreDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public Book insert(BookDto bookDto) {
        Book temp = convertToDomain(bookDto);
        return bookDao.save(temp);
    }

    @Override
    public List<Book> showAllRows() {
        return bookDao.findAll();
    }

    @Override
    public Book showById(long id) {
        return getBookById(id);
    }

    @Override
    public void deleteById(long id) {
        if (bookDao.getById(id) == null) {
            throw new IllegalBookException(id);
        }
        String bookname = bookDao.getById(id).getName();
        bookDao.deleteById(id);
    }

    private Book getBookById(long id) throws IllegalBookException {
        Book temp = bookDao.getById(id);
        if (temp == null) throw new IllegalBookException(id);
        return temp;
    }

    private Book convertToDomain(BookDto bookDto) {
        Author tempAuthor = authorDao.getByName(bookDto.getAuthor());
        if (tempAuthor == null) tempAuthor = authorDao.save(new Author(bookDto.getAuthor()));
        Genre tempGenre = genreDao.getByName(bookDto.getGenre());
        if (tempGenre == null) tempGenre = genreDao.save(new Genre(bookDto.getGenre()));
        return new Book(bookDto.getName(), tempAuthor, tempGenre);
    }

}
