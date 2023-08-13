package com.example.lucene.exception;

public class CustomIndexUserOperationException  extends CustomIndexUserException {
  public CustomIndexUserOperationException(String errorMessage) {
    super(errorMessage);
  }
}
