package ru.otus.spring.homework12.exceptions;

public class IllegalCommentException extends RuntimeException {
    public IllegalCommentException() {
        super("Комментариев не найдено");
    }
}
