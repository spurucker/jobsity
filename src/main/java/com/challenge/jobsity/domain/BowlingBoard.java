package com.challenge.jobsity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BowlingBoard {
  private List<Player> players;
}
