package ru.otus.spring.homework11.listener;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.spring.homework11.domain.Author;
import ru.otus.spring.homework11.domain.Book;
import ru.otus.spring.homework11.storage.BookDao;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteBookEventListener extends AbstractMongoEventListener<Author> {

    private final BookDao bookDao;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        val authorId = event.getSource().get("_id").toString();
        Flux<Book> list = bookDao.getByAuthorId(authorId);
        list.doOnEach(temp -> bookDao.deleteById(temp.get().getId()));
    }

}
