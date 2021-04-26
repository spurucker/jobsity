package com.challenge.jobsity.validator;

import com.challenge.jobsity.domain.Shot;
import com.challenge.jobsity.exception.BowlingValidationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BowlingValidator {
  private static final int MIN_SHOTS = 11;
  private static final int MAX_SHOTS = 23;
  private static final int MIN_PLAYERS = 2;

  public void validateShots(List<Shot> shots) throws BowlingValidationException {
    Map<String, List<Shot>> shotsByPlayer =
        shots.stream().collect(Collectors.groupingBy(Shot::getName));

    validatePinFalls(shots);
    validateMinPlayers(shotsByPlayer);
    validateAllPlayersShotsSize(shotsByPlayer);
    validateAllPlayerFrameMaxValue(shotsByPlayer);
  }

  private void validateMinPlayers(Map<String, List<Shot>> shotsByPlayer) throws BowlingValidationException {
    if (shotsByPlayer.size() < MIN_PLAYERS) {
      throw new BowlingValidationException("At least two players are required");
    }
  }

  private void validateAllPlayersShotsSize(Map<String, List<Shot>> shotsByPlayer) throws BowlingValidationException {
    for (Map.Entry<String, List<Shot>> entry : shotsByPlayer.entrySet()) {
      validatePlayerShotsSize(entry.getKey(), entry.getValue());
    }
  }

  private void validatePlayerShotsSize(String name, List<Shot> shots) throws BowlingValidationException {
    if (shots.size() < MIN_SHOTS || shots.size() > MAX_SHOTS) {
      throw new BowlingValidationException(
          String.format("Player %s can't have less than 11 nor more than 23 shots", name));
    }
  }

  private void validatePinFalls(List<Shot> shots) throws BowlingValidationException {
    for (Shot shot : shots) {
      if (shot.getPinFalls() > 10 || shot.getPinFalls() < 0) {
        throw new BowlingValidationException("Pin falls value has to be between 0 and 10");
      }
    }
  }

  private void validateAllPlayerFrameMaxValue(Map<String, List<Shot>> shotsByPlayer) throws BowlingValidationException {
    for (Map.Entry<String, List<Shot>> entry : shotsByPlayer.entrySet()) {
      validatePlayerFrameMaxValue(entry.getKey(), entry.getValue());
    }
  }

  private void validatePlayerFrameMaxValue(String name, List<Shot> shots) throws BowlingValidationException {
    int round = 1;
    int i = 0;
    for (; i < shots.size() && round < 10; i++) {
      Integer actualPinFalls = shots.get(i).getPinFalls();
      Integer nextPinFalls = shots.size() > i + 1 ? shots.get(i + 1).getPinFalls() : 0;
      if (actualPinFalls != 10 && actualPinFalls + nextPinFalls > 10) {
        throw new BowlingValidationException(
            String.format("Player %s has too many pin falls in the frame %d", name, round));
      }
      if (actualPinFalls != 10) {
        i++;
      }
      round++;
    }
    validatePlayerMaxRounds(name, shots.size(), i);
  }

  private void validatePlayerMaxRounds(String name, int shotsSize, int ninthRoundShotIndex) throws BowlingValidationException {
    if (shotsSize > ninthRoundShotIndex + 3) {
      throw new BowlingValidationException(String.format("Player %s has too many shots", name));
    }
  }
}
