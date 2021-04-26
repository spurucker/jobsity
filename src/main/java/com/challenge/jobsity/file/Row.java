package com.challenge.jobsity.file;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Row {
  private Map<String, String> values;

  public void put(String key, String value) {
    this.values.put(key, value);
  }

  public String get(String key) {
    return this.values.get(key);
  }
}
