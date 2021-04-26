package com.challenge.jobsity.exception;

public class FileReadingException extends Exception {
  public FileReadingException(String message, Exception e) {
    super(message, e);
  }
}
