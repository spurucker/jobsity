package com.challenge.jobsity.service;

import com.challenge.jobsity.domain.BowlingBoard;
import com.challenge.jobsity.file.BowlingFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

import static com.challenge.jobsity.TestConstants.TEST_FILE_PATH;
import static com.challenge.jobsity.fixture.BowlingBoardFixture.dummyBowlingBoard;
import static com.challenge.jobsity.fixture.ShotFixture.dummyShots;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BowlingServiceTest {
  private BowlingService bowlingService;

  @MockBean private BowlingFileReader bowlingFileReader;

  @PostConstruct
  public void init() {
    bowlingService = new BowlingService(bowlingFileReader);
  }

  @Test
  public void getBowlingBoard() throws Exception {
    when(bowlingFileReader.getShots(TEST_FILE_PATH)).thenReturn(dummyShots());

    BowlingBoard board = bowlingService.getBowlingBoard(TEST_FILE_PATH);

    assertThat(board).isEqualTo(dummyBowlingBoard());
  }

  @Test
  public void getBowlingBoardReaderException() throws Exception {
    String errorMessage = "some error";
    when(bowlingFileReader.getShots(TEST_FILE_PATH)).thenThrow(new Exception(errorMessage));

    assertThatThrownBy(() -> bowlingService.getBowlingBoard(TEST_FILE_PATH))
        .isInstanceOf(Exception.class)
        .hasMessage(errorMessage);
  }
}
