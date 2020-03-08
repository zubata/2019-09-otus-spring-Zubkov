package ru.otus.spring.homework18.domain;

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
    @Column(name = "author_name", nullable = false)
    private String name;

    @Override
    public String toString() {
        return "id =" + id +
                ", автор = '" + name + "'";
    }
}
