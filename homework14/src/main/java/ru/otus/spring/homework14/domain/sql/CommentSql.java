package ru.otus.spring.homework14.domain.sql;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "comment")
@Table(name = "comments")
public class CommentSql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "book_id")
    private BookSql book;

    @Column(name = "comment")
    private String comment;

    @Override
    public String toString() {
        return "id = " + id +
                ", книга = " + book.getName() +
                ", комментарий = '" + comment + "'";
    }
}
