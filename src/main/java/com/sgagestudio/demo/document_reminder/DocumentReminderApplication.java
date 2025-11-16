package com.sgagestudio.demo.document_reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DocumentReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentReminderApplication.class, args);
	}

}
