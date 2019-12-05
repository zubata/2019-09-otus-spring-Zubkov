package ru.otus.spring.homework13.exceptions;

public class IllegalCommentException extends RuntimeException {
    public IllegalCommentException() {
        super("Комментариев не найдено");
    }
}
