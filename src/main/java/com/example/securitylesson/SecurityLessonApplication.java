package com.example.securitylesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SecurityLessonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityLessonApplication.class, args);
	}

}
