package com.challenge.jobsity.file;

import com.challenge.jobsity.domain.Shot;
import com.challenge.jobsity.exception.FileReadingException;
import com.challenge.jobsity.exception.InvalidFilePathException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.challenge.jobsity.Constants.NAME;
import static com.challenge.jobsity.Constants.PIN_FALLS;
import static com.challenge.jobsity.TestConstants.TEST_FILE_PATH;
import static com.challenge.jobsity.fixture.RowFixture.dummyFShotRow;
import static com.challenge.jobsity.fixture.RowFixture.dummyIntegerShotRow;
import static com.challenge.jobsity.fixture.RowFixture.dummyXShotRow;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BowlingFileReaderTest {
  private static final List<String> TEST_SCHEMA = asList(NAME, PIN_FALLS);

  private BowlingFileReader bowlingFileReader;

  @MockBean private CsvDataLoaderService csvDataLoaderService;
  @MockBean private FileUtils fileUtils;

  @PostConstruct
  public void init() {
    bowlingFileReader = new BowlingFileReader(csvDataLoaderService, fileUtils);
  }

  @Test
  public void getShotsSingleFRoll() throws Exception {
    InputStream inputStreamMock = mock(InputStream.class);

    when(csvDataLoaderService.loadInput(eq(inputStreamMock), eq(TEST_SCHEMA)))
        .thenReturn(dummyFShotRow());
    when(fileUtils.createInputStream(eq(TEST_FILE_PATH))).thenReturn(inputStreamMock);

    List<Shot> got = bowlingFileReader.getShots(TEST_FILE_PATH);

    Shot expected = new Shot("Santiago", 0);
    assertThat(got).isEqualTo(asList(expected));
  }

  @Test
  public void getShotsSingleXRoll() throws Exception {
    InputStream inputStreamMock = mock(InputStream.class);

    when(csvDataLoaderService.loadInput(eq(inputStreamMock), eq(TEST_SCHEMA)))
        .thenReturn(dummyXShotRow());
    when(fileUtils.createInputStream(eq(TEST_FILE_PATH))).thenReturn(inputStreamMock);

    List<Shot> got = bowlingFileReader.getShots(TEST_FILE_PATH);

    Shot expected = new Shot("Santiago", 10);
    assertThat(got).isEqualTo(asList(expected));
  }

  @Test
  public void getShotsSingleIntRoll() throws Exception {
    InputStream inputStreamMock = mock(InputStream.class);

    when(csvDataLoaderService.loadInput(eq(inputStreamMock), eq(TEST_SCHEMA)))
        .thenReturn(dummyIntegerShotRow());
    when(fileUtils.createInputStream(eq(TEST_FILE_PATH))).thenReturn(inputStreamMock);

    List<Shot> got = bowlingFileReader.getShots(TEST_FILE_PATH);

    Shot expected = new Shot("Santiago", 1);
    assertThat(got).isEqualTo(asList(expected));
  }

  @Test
  public void getShotsInvalidFilePathException() throws FileNotFoundException {
    when(fileUtils.createInputStream(eq(TEST_FILE_PATH))).thenThrow(new FileNotFoundException());

    assertThatThrownBy(() -> bowlingFileReader.getShots(TEST_FILE_PATH))
        .isInstanceOf(InvalidFilePathException.class)
        .hasMessage("Could not find File in the path in: " + TEST_FILE_PATH);
  }

  @Test
  public void getShotsFileReadingException() throws IOException {
    InputStream inputStreamMock = mock(InputStream.class);

    when(csvDataLoaderService.loadInput(eq(inputStreamMock), eq(TEST_SCHEMA)))
        .thenThrow(new IOException());

    when(fileUtils.createInputStream(eq(TEST_FILE_PATH))).thenReturn(inputStreamMock);

    assertThatThrownBy(() -> bowlingFileReader.getShots(TEST_FILE_PATH))
        .isInstanceOf(FileReadingException.class)
        .hasMessage("File could not be read");
  }
}
