package ru.otus.spring.homework07.domain;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "genrename", nullable = false)
    private String genreName;

    @Override
    public String toString() {
        return "id = " + id +
                ", жанр = '" + genreName + "'";
    }
}
