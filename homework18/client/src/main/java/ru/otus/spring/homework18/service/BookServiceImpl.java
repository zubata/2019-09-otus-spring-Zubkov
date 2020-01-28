package ru.otus.spring.homework18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.homework18.domain.Author;
import ru.otus.spring.homework18.domain.Book;
import ru.otus.spring.homework18.domain.Genre;
import ru.otus.spring.homework18.dto.BookDto;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final RestTemplate restTemplate;

    @Value("${rest.fullUrl}")
    private String restUrl;

    @HystrixCommand(fallbackMethod = "insert_Fallback")
    @Override
    public Book insert(BookDto bookDto) {
        Book temp = convertToDomain(bookDto);
        ResponseEntity<Book> response = restTemplate.exchange(restUrl + "/api/book",
                HttpMethod.POST,
                new HttpEntity<Book>(temp),
                Book.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "showAllRows_Fallback")
    @Override
    public List<Book> showAllRows() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(restUrl + "/api/book",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "showById_Fallback")
    @Override
    public Book showById(long id) {
        ResponseEntity<Book> response = restTemplate.getForEntity(restUrl + "/api/book?id={id}" + id, Book.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "delete_Fallback")
    @Override
    public String delete(Book book) {
        restTemplate.delete(restUrl + "/api/book/id={id}", book.getId());
        return "Success deleting";
    }

    private Book convertToDomain(BookDto bookDto) {
        ResponseEntity<Author> responseAuthor = restTemplate.getForEntity(restUrl + "/api/author/name=" + bookDto.getAuthor(), Author.class);
        Author tempAuthor = responseAuthor.getBody();
        ResponseEntity<Genre> responseGenre = restTemplate.getForEntity(restUrl + "/api/genre/name=" + bookDto.getGenre(), Genre.class);
        Genre tempGenre = responseGenre.getBody();
        if (tempGenre == null) {
            responseGenre = restTemplate.exchange(restUrl + "/api/genre",
                    HttpMethod.POST,
                    new HttpEntity<Genre>(new Genre(bookDto.getGenre())),
                    Genre.class);
            tempGenre = responseGenre.getBody();
        }
        return new Book(bookDto.getName(), tempAuthor, tempGenre);
    }

    public Book insert_Fallback(BookDto bookDto) {
        return new Book("Error with insert book", new Author("null"), new Genre("null"));
    }

    public List<Book> showAllRows_Fallback() {
        return Collections.singletonList(new Book("Error with show all books", new Author("null"), new Genre("null")));
    }

    public Book showById_Fallback(long id) {
        return new Book("Error with show book", new Author("null"), new Genre("null"));
    }

    public String delete_Fallback(Book book) {
        return "Error with delete book";
    }

}
