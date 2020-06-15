package ru.otus.project.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Код ошибки 403 \nВам не хватает прав для этой команды");
    }

}
