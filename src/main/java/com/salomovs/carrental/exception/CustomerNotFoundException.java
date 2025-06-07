package com.salomovs.carrental.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException() {
    super("Customer Not Found!");
  }

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
