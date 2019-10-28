package ru.otus.spring.homework09.exceptions;

public class IllegalCommentException extends RuntimeException {
    public IllegalCommentException() {
        super("Комментариев не найдено");
    }
}
