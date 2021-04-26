package com.challenge.jobsity.fixture;

import com.challenge.jobsity.domain.Shot;

import java.util.List;

import static java.util.Arrays.asList;

public class ShotFixture {
  public static List<Shot> dummyShots() {
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
}
