package com.nocode.main.exception;

public class EncryptionException extends RuntimeException {
      public EncryptionException(String message, Throwable cause) {
          super(message, cause);
      }
  
      public EncryptionException(String message) {
          super(message);
      }
  }
