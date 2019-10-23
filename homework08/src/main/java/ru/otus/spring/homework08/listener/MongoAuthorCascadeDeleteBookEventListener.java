package ru.otus.spring.homework08.listener;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.storage.BookDao;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteBookEventListener extends AbstractMongoEventListener<Author> {

    private final BookDao bookDao;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        val authorId = event.getSource().get("_id").toString();
        List<Book> list = bookDao.getByAuthorId(authorId);
        list.forEach(temp -> bookDao.deleteById(temp.getId()));
    }
}
