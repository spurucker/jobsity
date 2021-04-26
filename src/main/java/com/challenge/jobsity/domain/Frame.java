package com.challenge.jobsity.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.challenge.jobsity.Constants.PIN_SIZE;

@Data
@Builder
public class Frame {
  private List<Integer> rolls;
  private Integer score;

  public boolean isStrike() {
    return rolls.size() == 1 && rolls.get(0).equals(PIN_SIZE);
  }

  public boolean isSpare() {
    return rolls.size() > 1 && (rolls.get(0) + rolls.get(1)) == PIN_SIZE;
  }

  public boolean isNotStrike() {
    return !isStrike();
  }
}
