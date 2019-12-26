package ru.otus.spring.homework15.exceptions;

public class IllegalCommentException extends RuntimeException {
    public IllegalCommentException() {
        super("Комментариев не найдено");
    }
}
