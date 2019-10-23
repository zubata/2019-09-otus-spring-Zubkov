package ru.otus.spring.homework08.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.storage.AuthorDao;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventListener extends AbstractMongoEventListener<Book> {

    private final AuthorDao authorDao;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        Book book = event.getSource();
        String authorName = book.getAuthor().getAuthorName();
        Author tempAuthor = authorDao.getByAuthorName(authorName);
        if (tempAuthor == null) authorDao.save(book.getAuthor());
        else book.setAuthor(tempAuthor);
    }
}
