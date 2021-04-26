package com.challenge.jobsity.service;

import com.challenge.jobsity.domain.BowlingBoard;
import com.challenge.jobsity.exception.BowlingValidationException;
import com.challenge.jobsity.file.BowlingFileReader;
import com.challenge.jobsity.validator.BowlingValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

import static com.challenge.jobsity.TestConstants.TEST_FILE_PATH;
import static com.challenge.jobsity.fixture.BowlingBoardFixture.dummyBowlingBoard;
import static com.challenge.jobsity.fixture.ShotFixture.dummySinglePlayerShots;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BowlingServiceTest {
  private BowlingService bowlingService;

  @MockBean private BowlingFileReader bowlingFileReader;
  @MockBean private BowlingValidator bowlingValidator;

  @PostConstruct
  public void init() {
    bowlingService = new BowlingService(bowlingValidator, bowlingFileReader);
  }

  @Test
  public void getBowlingBoard() throws Exception {
    when(bowlingFileReader.getShots(eq(TEST_FILE_PATH))).thenReturn(dummySinglePlayerShots());

    BowlingBoard board = bowlingService.getBowlingBoard(TEST_FILE_PATH);

    assertThat(board).isEqualTo(dummyBowlingBoard());

    verify(bowlingFileReader).getShots(eq(TEST_FILE_PATH));
    verify(bowlingValidator).validateShots(eq(dummySinglePlayerShots()));
  }

  @Test
  public void getBowlingBoardValidationException() throws Exception {
    String errorMessage = "some error";

    when(bowlingFileReader.getShots(eq(TEST_FILE_PATH))).thenReturn(dummySinglePlayerShots());
    doThrow(new BowlingValidationException(errorMessage))
        .when(bowlingValidator)
        .validateShots(eq(dummySinglePlayerShots()));

    assertThatThrownBy(() -> bowlingService.getBowlingBoard(TEST_FILE_PATH))
        .hasMessage(errorMessage)
        .isInstanceOf(BowlingValidationException.class);

    verify(bowlingFileReader).getShots(eq(TEST_FILE_PATH));
    verify(bowlingValidator).validateShots(eq(dummySinglePlayerShots()));
  }

  @Test
  public void getBowlingBoardReaderException() throws Exception {
    String errorMessage = "some error";
    when(bowlingFileReader.getShots(TEST_FILE_PATH)).thenThrow(new Exception(errorMessage));

    assertThatThrownBy(() -> bowlingService.getBowlingBoard(TEST_FILE_PATH))
        .isInstanceOf(Exception.class)
        .hasMessage(errorMessage);

    verify(bowlingFileReader).getShots(eq(TEST_FILE_PATH));
    verify(bowlingValidator, never()).validateShots(eq(dummySinglePlayerShots()));
  }
}
