package ru.otus.project.exception;

public class EmptyDirPath extends RuntimeException {
    public EmptyDirPath() {
        super("Не указан путь папки, куда сохранять файлы\n" +
                "сначала укажите путь при помощи команды sfb");
    }
}
