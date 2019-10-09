package ru.otus.spring.homework07.domain;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "authorname", nullable = false)
    private String authorName;

    @Override
    public String toString() {
        return "id =" + id +
                ", автор = '" + authorName + "'";
    }
}
