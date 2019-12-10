package ru.otus.spring.homework14.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework14.domain.CommentDto;
import ru.otus.spring.homework14.domain.Book;
import ru.otus.spring.homework14.domain.Comment;
import ru.otus.spring.homework14.storage.BookDao;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final BookDao bookDao;

    @Override
    public Comment convertToDomain(CommentDto commentDto) {
        Book book = bookDao.getByName(commentDto.getBook());
        Comment temp = new Comment(book);
        temp.setId(commentDto.getId());
        temp.setComment(commentDto.getComment());
        return temp;
    }


}
