package com.challenge.jobsity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Player {
  private String name;
  private List<Frame> frames;
}
