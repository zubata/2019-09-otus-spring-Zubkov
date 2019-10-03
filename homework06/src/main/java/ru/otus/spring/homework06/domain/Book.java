package ru.otus.spring.homework06.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "bookname", nullable = false)
    private String bookName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(cascade = CascadeType.ALL)
    @NonNull
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Override
    public String toString() {
        return "id = " + id +
                ", название = '" + bookName + '\'' +
                ", автор = " + author.getAuthorName() +
                ", жанр = " + genre.getGenreName();
    }
}
