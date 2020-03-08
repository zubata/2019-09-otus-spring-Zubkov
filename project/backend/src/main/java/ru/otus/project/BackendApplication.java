package ru.otus.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
public class BackendApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BackendApplication.class, args);
	}

}
