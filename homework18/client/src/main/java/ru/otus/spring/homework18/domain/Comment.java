package ru.otus.spring.homework18.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {
    private long id;

    @NonNull
    private Book book;
    private String comment;

    public Comment(String comment) { this.comment = comment;    }

    @Override
    public String toString() {
        return "id = " + id +
                ", книга = " + book.getName() +
                ", комментарий = '" + comment + "'";
    }
}
