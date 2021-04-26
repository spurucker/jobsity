package com.challenge.jobsity.fixture;

import com.challenge.jobsity.file.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.challenge.jobsity.Constants.NAME;
import static com.challenge.jobsity.Constants.PIN_FALLS;
import static java.util.Arrays.asList;

public class RowFixture {
  public static List<Row> dummyFShotRow() {
    Map<String, String> rowValue = new HashMap<>();
    rowValue.put(NAME, "Santiago");
    rowValue.put(PIN_FALLS, "F");
    return asList(Row.builder().values(rowValue).build());
  }

  public static List<Row> dummyXShotRow() {
    Map<String, String> rowValue = new HashMap<>();
    rowValue.put(NAME, "Santiago");
    rowValue.put(PIN_FALLS, "X");
    return asList(Row.builder().values(rowValue).build());
  }

  public static List<Row> dummyIntegerShotRow() {
    Map<String, String> rowValue = new HashMap<>();
    rowValue.put(NAME, "Santiago");
    rowValue.put(PIN_FALLS, "1");
    return asList(Row.builder().values(rowValue).build());
  }
}
