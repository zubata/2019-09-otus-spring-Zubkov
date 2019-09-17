package ru.otus.homework03.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework03.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест класса PersonDaoImpl ")
@SpringBootTest
class PersonDaoImplTest {

    @Autowired
    PersonDao personDao;

    @DisplayName(" метод findPerson")
    @Test
    void testfindPerson() {
        String firstName = "Ivan";
        String secondName = "Ivanov";
        Person testperson = personDao.findPerson(firstName,secondName);
        assertThat(testperson.getFirstName()).isEqualTo("Ivan");
        assertThat(testperson.getSecondName()).isEqualTo("Ivanov");
    }
}