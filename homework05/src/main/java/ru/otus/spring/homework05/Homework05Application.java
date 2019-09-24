package ru.otus.spring.homework05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.homework05.storage.AuthorDao;
import ru.otus.spring.homework05.storage.BookDao;
import ru.otus.spring.homework05.storage.GenreDao;

@SpringBootApplication
public class Homework05Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Homework05Application.class);
/*
        AuthorDao authorDao = context.getBean(AuthorDao.class);
		*//*authorDao.insert(new Author(3,"Rey Bradbury"));
		List list1 = authorDao.getAll();
		for (Object b : list1) {
			Author s = (Author) b;
			System.out.println(s.getName());
		}*//*

        BookDao bookDao = context.getBean(BookDao.class);
        *//*bookDao.delete(1);
        List list2 = bookDao.getAll();
        for (Object b : list2) {
            Book s = (Book) b;
            System.out.println(s.getBookName() + " автор " + s.getAuthorName());
        }*//*

        GenreDao genreDao = context.getBean(GenreDao.class);
        *//*List list3 = genreDao.getAll();
        for (Object b : list3) {
            Genre s = (Genre) b;
            System.out.println(s.getGenreName() + ", название книги: " + s.getBookName());
        }*//*

        System.out.println(String.format("количиство записей в author %d", authorDao.count()));
        System.out.println(String.format("количиство записей в books %d", bookDao.count()));
        System.out.println(String.format("количиство записей в genres %d", genreDao.count()));*/
    }

}
