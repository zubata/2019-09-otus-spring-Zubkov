package ru.otus.spring.homework14.domain.sql;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "author")
@NoArgsConstructor
@Table(name = "authors")
public class AuthorSql {
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
