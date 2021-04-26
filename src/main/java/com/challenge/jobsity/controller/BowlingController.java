package com.challenge.jobsity.controller;

import com.challenge.jobsity.domain.BowlingBoard;
import com.challenge.jobsity.domain.Frame;
import com.challenge.jobsity.domain.Player;
import com.challenge.jobsity.service.BowlingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.challenge.jobsity.Constants.FRAME_ROW_NAME;
import static com.challenge.jobsity.Constants.PIN_FALLS_ROW_NAME;
import static com.challenge.jobsity.Constants.PIN_SIZE;
import static com.challenge.jobsity.Constants.ROUNDS_SIZE;
import static com.challenge.jobsity.Constants.SCORE_ROW_NAME;

@Service
@RequiredArgsConstructor
public class BowlingController {
  private final BowlingService bowlingService;

  public String getGameResult(String filePath) throws Exception {
    BowlingBoard bowlingBoard = bowlingService.getBowlingBoard(filePath);
    return getPrintableGame(bowlingBoard);
  }

  private String getPrintableGame(BowlingBoard bowlingBoard) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(nameColumn(FRAME_ROW_NAME)).append(getFrameNumbers(ROUNDS_SIZE)).append('\n');
    bowlingBoard
        .getPlayers()
        .forEach(
            player -> {
              stringBuilder.append(nameColumn(player.getName())).append('\n');
              stringBuilder.append(pinFallsRow(player));
              stringBuilder.append(scoreRow(player));
              stringBuilder.append('\n');
            });
    return stringBuilder.toString();
  }

  private String pinFallsRow(Player player){
    return nameColumn(PIN_FALLS_ROW_NAME) + allPinFalls(player.getFrames()) + '\n';
  }

  private String scoreRow(Player player){
    return nameColumn(SCORE_ROW_NAME) + allScores(player.getFrames()) + '\n';
  }

  private String allScores(List<Frame> frames) {
    return frames.stream().map(f -> frameColumn(f.getScore())).collect(Collectors.joining());
  }

  private String allPinFalls(List<Frame> frames) {
    return frames.stream().map(this::pinFalls).collect(Collectors.joining());
  }

  private String pinFalls(Frame frame) {
    if (frame.getRolls().size() == 1) {
      return pinFallsColumn("") + pinFallsColumn("") + pinFallsColumn("X");
    }
    StringBuilder stringBuilder = new StringBuilder();
    if (frame.isNotStrike() && frame.getRolls().size() != 3) {
      stringBuilder.append(pinFallsColumn(""));
    }

    for (int i = 0; i < frame.getRolls().size(); i++) {
      if (i == 1 && frame.isSpare()) {
        stringBuilder.append(pinFallsColumn("\\"));
      } else {
        stringBuilder.append(pinFallsColumn(frame.getRolls().get(i)));
      }
    }
    return stringBuilder.toString();
  }

  private String getFrameNumbers(int size) {
    StringBuilder stringBuilder = new StringBuilder();
    IntStream.range(1, size + 1)
        .forEach(i -> stringBuilder.append(frameColumn(Integer.toString(i))));
    return stringBuilder.toString();
  }

  private String nameColumn(String value) {
    return StringUtils.leftPad(value, 10, '-');
  }

  private String frameColumn(String value) {
    return StringUtils.leftPad(value, 9, '-');
  }

  private String frameColumn(Integer value) {
    return StringUtils.leftPad(Integer.toString(value), 9, '-');
  }

  private String pinFallsColumn(String value) {
    return StringUtils.leftPad(value, 3, '-');
  }

  private String pinFallsColumn(Integer value) {
    return StringUtils.leftPad(pinFallsToString(value), 3, '-');
  }

  private String pinFallsToString(Integer value) {
    if (value.equals(PIN_SIZE)) {
      return "X";
    }
    return Integer.toString(value);
  }
}
