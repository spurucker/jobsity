package com.challenge.jobsity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;

@Slf4j
@SpringBootApplication
public class JobsityApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobsityApplication.class, args);
	}
}
