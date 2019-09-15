package ru.otus.homework03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework03.service.PollService;
import ru.otus.homework03.service.PollServiceImpl;

import java.io.IOException;

@SpringBootApplication
public class Homework03Application {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Homework03Application.class, args);
		PollService poll = context.getBean(PollServiceImpl.class);
		poll.testing();
	}

}
