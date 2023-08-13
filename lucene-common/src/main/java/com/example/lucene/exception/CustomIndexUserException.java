package com.example.lucene.exception;

public abstract class CustomIndexUserException extends RuntimeException {
  public CustomIndexUserException(String errorMessage) {
    super(errorMessage);
  }
}
