package ru.otus.spring.homework08.listener;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Comment;
import ru.otus.spring.homework08.exceptions.NoDeleteBookException;
import ru.otus.spring.homework08.service.IOService;
import ru.otus.spring.homework08.storage.CommentDao;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {

    private final CommentDao commentDao;
    private final IOService ioService;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        val bookname = event.getSource().get("_id").toString();
        List<Comment> list = commentDao.getByBookId(bookname);
        if (list.size() != 0) {
            ioService.output("Имеются комменатрии к книге, которые будут удалены, продолжить? [Y(да)]");
            if (ioService.input().toLowerCase().equals("y")) list.forEach(temp -> commentDao.deleteById(temp.getId()));
            else throw new NoDeleteBookException();
        }
    }
}
