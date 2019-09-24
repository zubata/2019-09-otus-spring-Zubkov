package ru.otus.spring.homework05.domain;

public class Author {
    private long id;
    private String authorName;

    public Author(long id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }
}
