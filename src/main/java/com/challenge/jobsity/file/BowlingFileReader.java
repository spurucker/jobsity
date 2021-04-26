package com.challenge.jobsity.file;

import com.challenge.jobsity.domain.Shot;
import com.challenge.jobsity.exception.FileReadingException;
import com.challenge.jobsity.exception.InvalidFilePathException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.challenge.jobsity.Constants.NAME;
import static com.challenge.jobsity.Constants.PIN_FALLS;
import static com.challenge.jobsity.Constants.PIN_SIZE;
import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class BowlingFileReader {
  private static final List<String> SCHEMA = asList(NAME, PIN_FALLS);

  private final CsvDataLoaderService csvDataLoaderService;
  private final FileUtils fileUtils;

  public List<Shot> getShots(String filePath) throws Exception {
    try {
      List<Shot> shots = new ArrayList<>();

      InputStream fileInputStream = fileUtils.createInputStream(filePath);
      List<Row> rows = csvDataLoaderService.loadInput(fileInputStream, SCHEMA);

      rows.forEach(r -> shots.add(fromRowToPinFall(r)));

      return shots;
    } catch (FileNotFoundException e) {
      throw new InvalidFilePathException("Could not find File in the path in: " + filePath);
    } catch (IOException e) {
      throw new FileReadingException("File could not be read", e);
    }
  }

  private Shot fromRowToPinFall(Row row) {
    Integer pinFalls = pinFallsToInteger(row.get(PIN_FALLS));
    return new Shot(row.get(NAME), pinFalls);
  }

  private Integer pinFallsToInteger(String pinFalls) {
    if (pinFalls.equalsIgnoreCase("F")) {
      return 0;
    }
    if (pinFalls.equalsIgnoreCase("X")) {
      return PIN_SIZE;
    }
    return Integer.parseInt(pinFalls);
  }
}
