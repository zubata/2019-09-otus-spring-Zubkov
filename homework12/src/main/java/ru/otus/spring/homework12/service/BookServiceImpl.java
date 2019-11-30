package ru.otus.spring.homework12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework12.domain.Author;
import ru.otus.spring.homework12.domain.Book;
import ru.otus.spring.homework12.domain.Genre;
import ru.otus.spring.homework12.dto.BookDto;
import ru.otus.spring.homework12.exceptions.IllegalBookException;
import ru.otus.spring.homework12.storage.AuthorDao;
import ru.otus.spring.homework12.storage.BookDao;
import ru.otus.spring.homework12.storage.GenreDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public String insert(BookDto bookDto) {
        Book temp = convertToDomain(bookDto);
        bookDao.save(temp);
        return String.format("Книга %s была успешно добавлена", temp.getName());
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
    public String deleteById(long id) {
        if (bookDao.getById(id) == null) {
            throw new IllegalBookException(id);
        }
        String bookname = bookDao.getById(id).getName();
        bookDao.deleteById(id);
        return String.format("Книга с названием %s удалена из БД", bookname);
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
