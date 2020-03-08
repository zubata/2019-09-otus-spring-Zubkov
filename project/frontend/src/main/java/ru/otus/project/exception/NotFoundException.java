package ru.otus.project.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super("Код ошибки 404 \n Такой url не обнаружен: " + message);
    }
}
