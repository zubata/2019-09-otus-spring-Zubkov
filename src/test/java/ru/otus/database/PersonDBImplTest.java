package ru.otus.database;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.domain.Person;

public class PersonDBImplTest {

    @Test
    public void findPerson() {
        PersonDB db = new PersonDBImpl();
        Person test = db.findPerson("Олег","Пупкин");
        Assert.assertEquals("Олег",test.getFirstName());
        Assert.assertEquals("Пупкин",test.getSecondName());
    }
}