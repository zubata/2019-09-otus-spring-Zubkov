package ru.otus.spring.homework05.domain;

public class Book {
    private long id;
    private String bookName;
    private String authorName;

    public Book(long id, String bookName, String authorName) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
