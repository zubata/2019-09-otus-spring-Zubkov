package ru.otus.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class FrontendWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendWebApplication.class, args);
	}

}
