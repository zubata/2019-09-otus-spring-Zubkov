package ru.otus.spring.homework15.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "comment")
    private String comment;

    @Override
    public String toString() {
        return "id = " + id +
                ", книга = " + book.getName() +
                ", комментарий = '" + comment + "'";
    }


}
