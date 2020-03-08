package ru.otus.spring.homework18.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;

    private String name;

    private Author author;

    private List<Genre> genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = Collections.singletonList(genre);
    }

    public void addGenre(Genre genre) {
        this.genre.add(genre);
    }

    public void remove(Genre genre) {
        this.genre.remove(genre);
    }

    public String getGenreList() {
        StringBuilder genresString = new StringBuilder();
        genre.forEach(genres -> genresString.append(genres.getName()).append(", "));
        return genresString.substring(0, genresString.length() - 2);
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", название = '" + name + '\'' +
                ", автор = " + author.getName() +
                ", жанр = " + getGenreList();
    }
}
