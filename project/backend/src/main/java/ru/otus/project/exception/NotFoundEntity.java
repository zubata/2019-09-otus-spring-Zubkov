package ru.otus.project.exception;

public class NotFoundEntity extends RuntimeException {

    public NotFoundEntity(String type, String message) {
        super(String.format("%s с таким %s не найдено", type, message));
    }
}
