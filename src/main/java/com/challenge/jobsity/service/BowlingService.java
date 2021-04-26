package com.challenge.jobsity.service;

import com.challenge.jobsity.domain.BowlingBoard;
import com.challenge.jobsity.domain.Frame;
import com.challenge.jobsity.domain.Player;
import com.challenge.jobsity.domain.Shot;
import com.challenge.jobsity.file.BowlingFileReader;
import com.challenge.jobsity.validator.BowlingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.challenge.jobsity.Constants.PIN_SIZE;
import static com.challenge.jobsity.Constants.ROUNDS_SIZE;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BowlingService {
  private final BowlingValidator bowlingValidator;
  private final BowlingFileReader bowlingFileReader;

  public BowlingBoard getBowlingBoard(String filePath) throws Exception {
    List<Shot> shots = bowlingFileReader.getShots(filePath);

    bowlingValidator.validateShots(shots);

    Map<String, List<Shot>> shotsByPlayer =
        shots.stream().collect(Collectors.groupingBy(Shot::getName));

    List<Player> players = new ArrayList<>();
    shotsByPlayer.forEach((name, playerShots) -> players.add(getPlayerGame(name, playerShots)));

    return new BowlingBoard(players);
  }

  private Player getPlayerGame(String name, List<Shot> shots) {
    List<Frame> frames = createFrames(shots);
    return new Player(name, frames);
  }

  private List<Frame> createFrames(List<Shot> shots) {
    List<Frame> frames = new ArrayList<>();
    int j = 0;
    Integer accumulated = 0;
    for (int i = 0; i < ROUNDS_SIZE; i++) {
      Shot shot = shots.get(j);
      Shot nextShot = shots.get(j + 1);
      Shot nextNextShot = shots.size() > j + 2 ? shots.get(j + 2) : null;

      accumulated = calculateFrameScore(accumulated, shot, nextShot, nextNextShot);
      List<Integer> rolls = getFrameRolls(i, shot, nextShot, nextNextShot);

      Frame frame = Frame.builder().rolls(rolls).score(accumulated).build();
      frames.add(frame);

      j += isNotStrike(shot) ? 2 : 1;
    }
    return frames;
  }

  private Integer calculateFrameScore(
      Integer accumulated, Shot shot, Shot nextShot, Shot nextNextShot) {
    accumulated = sumValues(accumulated, shot.getPinFalls(), nextShot.getPinFalls());
    if (isStrike(shot) || isSpare(shot, nextShot)) {
      accumulated = sumValues(accumulated, nextNextShot.getPinFalls());
    }
    return accumulated;
  }

  private List<Integer> getFrameRolls(int round, Shot shot, Shot nextShot, Shot nextNextShot) {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(shot.getPinFalls());
    if (isNotStrike(shot) || isLastRound(round)) {
      rolls.add(nextShot.getPinFalls());
    }

    if (hasExtraShot(round, nextNextShot)) {
      rolls.add(nextNextShot.getPinFalls());
    }
    return rolls;
  }

  private boolean hasExtraShot(int round, Shot extraShot) {
    return isLastRound(round) && nonNull(extraShot);
  }

  private boolean isLastRound(int round) {
    return round == ROUNDS_SIZE - 1;
  }

  private Integer sumValues(Integer... values) {
    return Arrays.stream(values).reduce(0, Integer::sum);
  }

  private boolean isSpare(Shot firstShot, Shot secondShot) {
    return hitAllPins(sumValues(firstShot.getPinFalls(), secondShot.getPinFalls()));
  }

  private boolean isStrike(Shot shot) {
    return hitAllPins(shot.getPinFalls());
  }

  private boolean isNotStrike(Shot shot) {
    return !hitAllPins(shot.getPinFalls());
  }

  private boolean hitAllPins(Integer pinFalls) {
    return pinFalls.equals(PIN_SIZE);
  }
}
