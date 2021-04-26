package com.challenge.jobsity;

import com.challenge.jobsity.controller.BowlingController;
import com.challenge.jobsity.exception.BowlingValidationException;
import com.challenge.jobsity.file.BowlingFileReader;
import com.challenge.jobsity.file.CsvDataLoaderService;
import com.challenge.jobsity.file.FileUtils;
import com.challenge.jobsity.service.BowlingService;
import com.challenge.jobsity.validator.BowlingValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;

import static com.challenge.jobsity.ResourceReader.asString;
import static com.challenge.jobsity.TestConstants.TEST_NORMAL_FILE_PATH;
import static com.challenge.jobsity.TestConstants.TEST_PERFECT_SCORE_FILE_PATH;
import static com.challenge.jobsity.TestConstants.TEST_SIMPLE_FILE_PATH;
import static com.challenge.jobsity.TestConstants.TEST_ZERO_SCORE_FILE_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BowlingIntegrationTest {

  @Autowired public BowlingController bowlingController;
  @MockBean private FileUtils fileUtils;
  @Value("classpath:output/expectedNormalGame.txt")
  private Resource expectedNormalResult;
  @Value("classpath:output/expectedPerfectScoreGame.txt")
  private Resource expectedPerfectResult;
  @Value("classpath:output/expectedZeroScoreGame.txt")
  private Resource expectedZeroResult;

  @Test
  public void normalGame() throws Exception {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(TEST_NORMAL_FILE_PATH);

    when(fileUtils.createInputStream(eq(TEST_NORMAL_FILE_PATH))).thenReturn(inputStream);

    String expected = asString(expectedNormalResult);

    String got = bowlingController.getGameResult(TEST_NORMAL_FILE_PATH);

    assertThat(got).isEqualToIgnoringNewLines(expected);
  }

  @Test
  public void zeroScoreGame() throws Exception {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(TEST_ZERO_SCORE_FILE_PATH);

    when(fileUtils.createInputStream(eq(TEST_ZERO_SCORE_FILE_PATH))).thenReturn(inputStream);

    String expected = asString(expectedZeroResult);

    String got = bowlingController.getGameResult(TEST_ZERO_SCORE_FILE_PATH);

    assertThat(got).isEqualToIgnoringNewLines(expected);
  }

  @Test
  public void perfectScoreGame() throws Exception {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(TEST_PERFECT_SCORE_FILE_PATH);

    when(fileUtils.createInputStream(eq(TEST_PERFECT_SCORE_FILE_PATH))).thenReturn(inputStream);

    String expected = asString(expectedPerfectResult);

    String got = bowlingController.getGameResult(TEST_PERFECT_SCORE_FILE_PATH);

    assertThat(got).isEqualToIgnoringNewLines(expected);
  }

  @Test
  public void invalidFileGame() throws Exception {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(TEST_SIMPLE_FILE_PATH);

    when(fileUtils.createInputStream(eq(TEST_SIMPLE_FILE_PATH))).thenReturn(inputStream);

    assertThatThrownBy(() -> bowlingController.getGameResult(TEST_SIMPLE_FILE_PATH))
        .isInstanceOf(BowlingValidationException.class)
        .hasMessage("At least two players are required");
  }


  @TestConfiguration
  static class TestContextConfiguration {
    @Bean
    public BowlingController bowlingController(BowlingService service) {
      return new BowlingController(service);
    }

    @Bean
    public BowlingService bowlingService(BowlingValidator validator, BowlingFileReader reader) {
      return new BowlingService(validator, reader);
    }

    @Bean
    public BowlingValidator bowlingValidator() {
      return new BowlingValidator();
    }

    @Bean
    public CsvDataLoaderService csvDataLoaderService() {
      return new CsvDataLoaderService();
    }

    @Bean
    public BowlingFileReader bowlingFileReader(
        FileUtils fileUtils, CsvDataLoaderService csvDataLoaderService) {
      return new BowlingFileReader(fileUtils, csvDataLoaderService);
    }
  }
}
