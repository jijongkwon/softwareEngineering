package com.softwareEngineering.codeReview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodeReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeReviewApplication.class, args);
	}

}
