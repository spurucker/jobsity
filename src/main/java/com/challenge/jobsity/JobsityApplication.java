package com.challenge.jobsity;

import com.challenge.jobsity.controller.BowlingController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@RequiredArgsConstructor
public class JobsityApplication implements CommandLineRunner {
  private final BowlingController bowlingController;

  public static void main(String[] args) {
    SpringApplication.run(JobsityApplication.class, args);
  }

  @Override
  public void run(String... args) {
    try {
      System.out.println(bowlingController.getGameResult(args[0]));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
