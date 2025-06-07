package com.salomovs.carrental.exception;

public class RentalNotFoundException extends RuntimeException {
  public RentalNotFoundException() {
    super("Rental Not Found!");
  }

  public RentalNotFoundException(String message) {
    super(message);
  }
}
