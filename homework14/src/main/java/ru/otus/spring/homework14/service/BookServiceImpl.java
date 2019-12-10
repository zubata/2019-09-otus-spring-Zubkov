package ru.otus.spring.homework14.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework14.domain.Author;
import ru.otus.spring.homework14.domain.Book;
import ru.otus.spring.homework14.domain.BookDto;
import ru.otus.spring.homework14.domain.Genre;
import ru.otus.spring.homework14.storage.AuthorDao;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final AuthorDao authorDao;

    @Override
    public Book convertToDomain(BookDto bookDto) {
        Author tempAuthor = authorDao.getByName(bookDto.getAuthor());
        Book book = new Book(bookDto.getName(), tempAuthor, new Genre(bookDto.getGenre()));
        book.setId(bookDto.getId());
        return book;
    }

}
