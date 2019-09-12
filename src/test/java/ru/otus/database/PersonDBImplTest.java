package ru.otus.database;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.domain.Person;

public class PersonDBImplTest {

    @Test
    public void findPerson() {
        PersonDao db = new PersonDaoImpl();
        Person test = db.findPerson("Олег","Пупкин");
        Assert.assertEquals("Олег",test.getFirstName());
        Assert.assertEquals("Пупкин",test.getSecondName());
    }
}