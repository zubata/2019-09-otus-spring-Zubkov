package ru.otus.spring.homework06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework06.service.CommentService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForComment {

    private final CommentService commentService;

    @ShellMethod(value = "insert comment", key = {"ic", "inc"})
    public String insert() {
        String result = commentService.insert();
        if (result==null) return null;
        return String.format("Комментарий для книги %s добавлен", result);
    }

    @ShellMethod(value = "show comment by id", key = "sci")
    public void showById() { commentService.showById(); }

    @ShellMethod(value = "show comment by name", key = "scn")
    public void showByName() { commentService.showByName(); }

    @ShellMethod(value = "show all comment", key = "sac")
    public void showAllRows() {
        commentService.showAllRows();
    }

    @ShellMethod(value = "delete comment by id", key = "dci")
    public String deleteById() {
        String id = commentService.deleteById();
        return String.format("Комментарий с id %s удален из БД", id);
    }

    @ShellMethod(value = "delete comment by book", key = "dcb")
    public String deleteByBook() {
        String result = commentService.deleteByBook();
        if (result==null) return null;
        return String.format("Комментарий для книги %s удален из БД", result);
    }

    @ShellMethod(value = "show count comment", key = "scc")
    public void showCount() {
        commentService.showCount();
    }
}
