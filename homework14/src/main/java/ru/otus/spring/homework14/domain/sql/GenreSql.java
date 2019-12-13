package ru.otus.spring.homework14.domain.sql;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "genre")
@NoArgsConstructor
@Table(name = "genres")
public class GenreSql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "genre_name", nullable = false)
    private String name;

    @Override
    public String toString() {
        return "id = " + id +
                ", жанр = '" + name + "'";
    }
}
