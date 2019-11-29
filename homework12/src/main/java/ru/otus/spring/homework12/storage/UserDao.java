package ru.otus.spring.homework12.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework12.domain.User;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
