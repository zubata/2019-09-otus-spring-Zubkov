package ru.otus.spring.homework18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.homework18.domain.Author;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "insert_Fallback")
    @Override
    public Author insert(Author author) {
        ResponseEntity<Author> response = restTemplate.postForEntity("http://localhost:8081/api/author", author, Author.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "showAllRows_Fallback")
    @Override
    public List<Author> showAllRows() {
        ResponseEntity<List<Author>> response = restTemplate.exchange("http://localhost:8081/api/author",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Author>>() {
                });
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "showById_Fallback")
    @Override
    public Author showById(long id) {
        ResponseEntity<Author> response = restTemplate.getForEntity("http://localhost:8081/api/author?id=" + id, Author.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "deleteById_Fallback")
    @Override
    public String delete(Author author) {
        restTemplate.delete("http://localhost:8081/api/author/id={id}", author.getId());
        return "Success deleting";
    }

    public List<Author> showAllRows_Fallback() {
        List<Author> err = new ArrayList<>();
        err.add(new Author("Error with show all authors"));
        return err;
    }

    public Author insert_Fallback(Author author) {
        return new Author("Error with insert author");
    }

    public Author showById_Fallback(long id) {
        return new Author("Error with get author");
    }

    public String deleteById_Fallback(Author author) { return "Error with delete author"; }

}
