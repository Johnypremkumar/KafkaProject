package com.kafkaMock.KafkaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class KafkaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
