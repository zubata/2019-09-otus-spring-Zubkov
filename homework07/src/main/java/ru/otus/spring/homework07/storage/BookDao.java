package ru.otus.spring.homework07.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework07.domain.Book;

public interface BookDao extends JpaRepository<Book,Long> {

    Book getById(long id);

    Book getBybookName(String bookname);

   // Book saveOrUpdate(Book book);

}
