package com.challenge.jobsity.file;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@AllArgsConstructor
public class CsvDataLoaderService {
  private static final char DELIMITER = '\t';
  private static final char SCAPE_CHAR = '\"';
  private static final char QUOTE_CHAR = '|';

  public List<Row> loadInput(InputStream inputStream, List<String> schema) throws IOException {
    CSVParser parser =
        new CSVParserBuilder()
            .withSeparator(DELIMITER)
            .withEscapeChar(SCAPE_CHAR)
            .withQuoteChar(QUOTE_CHAR)
            .withIgnoreQuotations(true)
            .build();

    CSVReader reader =
        new CSVReaderBuilder(new InputStreamReader(inputStream, UTF_8))
            .withCSVParser(parser)
            .build();

    return readCSVReader(reader, schema);
  }

  private List<Row> readCSVReader(CSVReader reader, List<String> schema) throws IOException {
    List<Row> list = new ArrayList<>();
    String[] line;

    while ((line = reader.readNext()) != null) {
      Row row = Row.builder().values(new HashMap<>()).build();

      for (int i = 0; i < line.length && i < schema.size(); i++) {
        row.put(schema.get(i), new String(line[i].getBytes(), UTF_8));
      }
      list.add(row);
    }
    return list;
  }
}
