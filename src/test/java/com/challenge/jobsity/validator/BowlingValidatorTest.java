package com.challenge.jobsity.validator;

import com.challenge.jobsity.exception.BowlingValidationException;
import org.junit.jupiter.api.Test;

import static com.challenge.jobsity.fixture.ShotFixture.dummySinglePlayerShots;
import static com.challenge.jobsity.fixture.ShotFixture.dummyTwoPlayerShots;
import static com.challenge.jobsity.fixture.ShotFixture.dummyTwoPlayersIncompleteShots;
import static com.challenge.jobsity.fixture.ShotFixture.dummyTwoPlayersInvalidShots;
import static com.challenge.jobsity.fixture.ShotFixture.dummyTwoPlayersTooManyPinFallsInARound;
import static com.challenge.jobsity.fixture.ShotFixture.dummyTwoPlayersTooManyShots;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingValidatorTest {
  private final BowlingValidator bowlingValidator = new BowlingValidator();

  @Test
  public void validateShots() throws BowlingValidationException {
    bowlingValidator.validateShots(dummyTwoPlayerShots());
  }

  @Test
  public void validateShotsFailAtLeastTwoPlayers() {
    assertThatThrownBy(() -> bowlingValidator.validateShots(dummySinglePlayerShots()))
        .isInstanceOf(BowlingValidationException.class)
        .hasMessage("At least two players are required");
  }

  @Test
  public void validateShotsFailEmptyShots() {
    assertThatThrownBy(() -> bowlingValidator.validateShots(dummyTwoPlayersIncompleteShots()))
        .isInstanceOf(BowlingValidationException.class)
        .hasMessage("Player Pedro can't have less than 11 nor more than 23 shots");
  }

  @Test
  public void validateShotsInvalidPinValueShots() {
    assertThatThrownBy(() -> bowlingValidator.validateShots(dummyTwoPlayersInvalidShots()))
        .isInstanceOf(BowlingValidationException.class)
        .hasMessage("Pin falls value has to be between 0 and 10");
  }

  @Test
  public void validateShotsPlayerHasTooManyShots() {
    assertThatThrownBy(() -> bowlingValidator.validateShots(dummyTwoPlayersTooManyShots()))
        .isInstanceOf(BowlingValidationException.class)
        .hasMessage("Player Pedro has too many shots");
  }

  @Test
  public void validateShotsPlayerTooManyPinFallsInARound() {
    assertThatThrownBy(
            () -> bowlingValidator.validateShots(dummyTwoPlayersTooManyPinFallsInARound()))
        .isInstanceOf(BowlingValidationException.class)
        .hasMessage("Player Pedro has too many pin falls in the frame 1");
  }
}
