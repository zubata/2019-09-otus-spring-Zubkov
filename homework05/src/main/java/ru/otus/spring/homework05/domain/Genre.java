package ru.otus.spring.homework05.domain;

public class Genre {
    private long id;
    private String genreName;
    private String bookName;

    public Genre(long id, String genreName, String bookName) {
        this.id = id;
        this.genreName = genreName;
        this.bookName = bookName;
    }

    public Genre(String genreName, String bookName) {
        this.genreName = genreName;
        this.bookName = bookName;
    }

    public long getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getBookName() {
        return bookName;
    }
}
