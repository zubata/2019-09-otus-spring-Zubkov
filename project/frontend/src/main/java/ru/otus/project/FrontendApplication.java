package ru.otus.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class FrontendApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FrontendApplication.class, args);
	}

}
