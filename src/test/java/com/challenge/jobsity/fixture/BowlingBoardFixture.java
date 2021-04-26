package com.challenge.jobsity.fixture;

import com.challenge.jobsity.domain.BowlingBoard;
import com.challenge.jobsity.domain.Frame;
import com.challenge.jobsity.domain.Player;

import java.util.List;

import static java.util.Arrays.asList;

public class BowlingBoardFixture {
  public static BowlingBoard dummyBowlingBoard() {
    return new BowlingBoard(asList(dummyPedroPlayer()));
  }

  public static Player dummyPedroPlayer() {
    return new Player("Pedro", dummyFramesLastRollsStrikes());
  }

  public static List<Frame> dummyFramesLastRollsStrikes() {
    return asList(
        Frame.builder().score(30).rolls(asList(10)).build(),
        Frame.builder().score(50).rolls(asList(10)).build(),
        Frame.builder().score(60).rolls(asList(10)).build(),
        Frame.builder().score(60).rolls(asList(0, 0)).build(),
        Frame.builder().score(70).rolls(asList(0, 10)).build(),
        Frame.builder().score(85).rolls(asList(0, 10)).build(),
        Frame.builder().score(105).rolls(asList(5, 5)).build(),
        Frame.builder().score(135).rolls(asList(10)).build(),
        Frame.builder().score(165).rolls(asList(10)).build(),
        Frame.builder().score(195).rolls(asList(10, 10, 10)).build());
  }
}
