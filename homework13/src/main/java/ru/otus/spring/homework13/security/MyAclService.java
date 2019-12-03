package ru.otus.spring.homework13.security;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.homework13.domain.Book;

public interface MyAclService {

    void createACL(Book book, boolean isAdultBook);

    void removeACL(long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean isAdmin();
}
