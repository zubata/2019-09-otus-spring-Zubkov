package ru.otus.homework03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework03.domain.Person;
import ru.otus.homework03.service.IOService;
import ru.otus.homework03.service.PersonService;
import ru.otus.homework03.service.PollService;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Тест контекста ")
public class Homework03ApplicationTests {

	@DisplayName("поднятие контекста")
	@Test
	public void contextLoads() throws IOException {
	}

}
