package ru.otus.spring.homework11.listener;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.otus.spring.homework11.domain.Book;
import ru.otus.spring.homework11.domain.Comment;
import ru.otus.spring.homework11.storage.CommentDao;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {

    private final CommentDao commentDao;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        val bookId = event.getSource().get("_id").toString();
        Flux<Comment> list = commentDao.getByBookId(bookId);
        list.doOnEach(temp -> commentDao.deleteById(temp.get().getId()))
                .subscribeOn(Schedulers.elastic());
    }
}
