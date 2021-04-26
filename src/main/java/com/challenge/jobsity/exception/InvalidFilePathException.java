package com.challenge.jobsity.exception;

public class InvalidFilePathException extends Exception {
  public InvalidFilePathException(String message) {
    super(message);
  }

  public InvalidFilePathException(String message, Exception e) {
    super(message);
  }
}
