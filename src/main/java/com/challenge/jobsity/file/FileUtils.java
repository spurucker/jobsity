package com.challenge.jobsity.file;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class FileUtils {
  public InputStream createInputStream(String filePath) throws FileNotFoundException {
    return new FileInputStream(filePath);
  }
}
