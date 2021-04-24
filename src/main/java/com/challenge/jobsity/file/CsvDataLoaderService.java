package com.challenge.jobsity.file;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class CsvDataLoaderService {
    @Value( "${file.delimiter}" )
    private char delimiter;
    @Value( "${file.charset}" )
    private Charset initCharSet;
    @Value( "${file.escape.char}" )
    private char escapeChar;
    @Value( "${file.quote.char}" )
    private char quoteChar;

    public List<Row> loadInput(File file, List<String> schema) throws IOException {
        InputStream is = new FileInputStream(file);
        return loadInput(is, schema);
    }

    public List<Row> loadInput(InputStream inputStream, List<String> schema) throws IOException {

        CSVParser parser = new CSVParserBuilder()
            .withSeparator(delimiter)
            .withEscapeChar(escapeChar)
            .withQuoteChar(quoteChar)
            .withIgnoreQuotations(true)
            .build();
        CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream, initCharSet))
            .withCSVParser(parser)
            .build();

        List<Row> list = readCSVReader(reader, schema);
        return list;
    }

    private List<Row> readCSVReader(CSVReader reader, List<String> schema) throws IOException {
        List<Row> list = new ArrayList<>();
        String[] line;

        while ((line = reader.readNext()) != null) {
            Row row = Row.builder().row(new HashMap<>()).build();

            for (int i = 0; i < line.length && i < schema.size(); i++) {
                row.put(schema.get(i), new String(line[i].getBytes(), UTF_8));
            }
            list.add(row);
        }
        return list;
    }
}
