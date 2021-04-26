package com.challenge.jobsity.domain;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

  @Test
  public void isStrike() {
    Frame frame = Frame.builder().rolls(asList(10)).build();

    assertThat(frame.isStrike()).isTrue();
  }

  @Test
  public void isNotStrike() {
    Frame frame = Frame.builder().rolls(asList(4, 3)).build();

    assertThat(frame.isNotStrike()).isTrue();
  }

  @Test
  public void isSpare() {
    Frame frame = Frame.builder().rolls(asList(4, 6)).build();

    assertThat(frame.isSpare()).isTrue();
  }

  @Test
  public void isNotSpare() {
    Frame frame = Frame.builder().rolls(asList(4, 4)).build();

    assertThat(frame.isSpare()).isFalse();
  }
}
