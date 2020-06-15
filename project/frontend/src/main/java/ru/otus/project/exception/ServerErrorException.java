package ru.otus.project.exception;

public class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super("Код ошибки 500 \nОшибка на стороне сервере\n" +
                "Описание: " + message);
    }

}
