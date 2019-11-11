package ru.otus.spring.homework10.exceptions;

public class IllegalCommentException extends RuntimeException {
    public IllegalCommentException() {
        super("Комментариев не найдено");
    }
}
