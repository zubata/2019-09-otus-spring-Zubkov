package ru.otus.spring.homework14.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "book")
@Table(name = "books")
@NamedEntityGraph(name = "BookWithAuthorAndGenre", attributeNodes =
        {@NamedAttributeNode(value = "author"), @NamedAttributeNode(value = "genre")})
public class BookSql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id")
    private AuthorSql author;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private List<GenreSql> genre;

    public BookSql(String name, AuthorSql author, GenreSql genre) {
        this.name = name;
        this.author = author;
        this.genre = Collections.singletonList(genre);
    }

    public void addGenre(GenreSql genre) { this.genre.add(genre); }

    public void remove(GenreSql genre) { this.genre.remove(genre); }

    public String getGenreList() {
        StringBuilder genresString = new StringBuilder();
        genre.forEach(genres -> genresString.append(genres.getName()).append(", "));
        return genresString.substring(0,genresString.length()-2);
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", название = '" + name + '\'' +
                ", автор = " + author.getName() +
                ", жанр = " + getGenreList();
    }
}
