package com.challenge.jobsity.fixture;

import com.challenge.jobsity.domain.Shot;

import java.util.List;

import static java.util.Arrays.asList;

public class ShotFixture {

  public static List<Shot> dummySinglePlayerShots() {
    return asList(
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 0),
        new Shot("Pedro", 0),
        new Shot("Pedro", 0),
        new Shot("Pedro", 10),
        new Shot("Pedro", 0),
        new Shot("Pedro", 10),
        new Shot("Pedro", 5),
        new Shot("Pedro", 5),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10));
  }

  public static List<Shot> dummyTwoPlayerShots() {
    return asList(
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 0),
        new Shot("Pedro", 0),
        new Shot("Pedro", 0),
        new Shot("Pedro", 10),
        new Shot("Pedro", 0),
        new Shot("Pedro", 10),
        new Shot("Pedro", 5),
        new Shot("Pedro", 5),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 5),
        new Shot("Juancito", 5),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10));
  }

  public static List<Shot> dummyTwoPlayersIncompleteShots() {
    return asList(new Shot("Pedro", 10), new Shot("Juancito", 10));
  }

  public static List<Shot> dummyTwoPlayersTooManyShots() {
    return asList(
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 5),
        new Shot("Juancito", 5),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10));
  }

  public static List<Shot> dummyTwoPlayersTooManyPinFallsInARound() {
    return asList(
        new Shot("Pedro", 5),
        new Shot("Pedro", 7),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 5),
        new Shot("Juancito", 5),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10));
  }

  public static List<Shot> dummyTwoPlayersInvalidShots() {
    return asList(
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 1),
        new Shot("Pedro", 15),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Pedro", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 0),
        new Shot("Juancito", 10),
        new Shot("Juancito", 5),
        new Shot("Juancito", 5),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10),
        new Shot("Juancito", 10));
  }
}
