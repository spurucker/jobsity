package com.challenge.jobsity.controller;

import com.challenge.jobsity.service.BowlingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

import static com.challenge.jobsity.ResourceReader.asString;
import static com.challenge.jobsity.TestConstants.TEST_SIMPLE_FILE_PATH;
import static com.challenge.jobsity.fixture.BowlingBoardFixture.dummyBowlingBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BowlingControllerTest {
  private BowlingController bowlingController;

  @MockBean private BowlingService bowlingService;

  @Value("classpath:output/expectedPrintable.txt")
  private Resource expectedPrint;

  @PostConstruct
  public void init() {
    bowlingController = new BowlingController(bowlingService);
  }

  @Test
  public void getBowlingBoard() throws Exception {
    String expected = asString(expectedPrint);

    when(bowlingService.getBowlingBoard(eq(TEST_SIMPLE_FILE_PATH))).thenReturn(dummyBowlingBoard());

    String got = bowlingController.getGameResult(TEST_SIMPLE_FILE_PATH);

    assertThat(got).isEqualToIgnoringNewLines(expected);
  }

  @Test
  public void getBowlingBoardException() throws Exception {
    String errorMessage = "some error";
    when(bowlingService.getBowlingBoard(eq(TEST_SIMPLE_FILE_PATH)))
        .thenThrow(new Exception(errorMessage));

    assertThatThrownBy(() -> bowlingController.getGameResult(TEST_SIMPLE_FILE_PATH))
        .isInstanceOf(Exception.class)
        .hasMessage(errorMessage);
  }
}
