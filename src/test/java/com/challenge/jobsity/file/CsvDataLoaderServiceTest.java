package com.challenge.jobsity.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.challenge.jobsity.TestConstants.TEST_FILE_PATH;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.apache.commons.collections.ListUtils.unmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvDataLoaderServiceTest {
  private static final char DELIMITER = '\t';
  private static final char SCAPE_CHAR = '\"';
  private static final char QUOTE_CHAR = '|';

  private static final List<String> SCHEMA = unmodifiableList(asList("name", "score"));

  private final CsvDataLoaderService csvDataLoaderService =
      new CsvDataLoaderService(DELIMITER, UTF_8, SCAPE_CHAR, QUOTE_CHAR);

  @Test
  public void loadInput() throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(TEST_FILE_PATH);

    List<Row> rowList = csvDataLoaderService.loadInput(inputStream, SCHEMA);

    assertThat(rowList.size()).isEqualTo(1);

    assertThat(rowList.get(0).get("name")).isEqualTo("Santiago");
    assertThat(rowList.get(0).get("score")).isEqualTo("10");
  }
}
