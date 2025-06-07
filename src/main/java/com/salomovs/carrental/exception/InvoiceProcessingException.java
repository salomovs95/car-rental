package com.salomovs.carrental.exception;

public class InvoiceProcessingException extends RuntimeException {
  public InvoiceProcessingException() {
    super("There was a problem processing your invoice");
  }

  public InvoiceProcessingException(String message) {
    super(message);
  }
}
