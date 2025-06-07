package com.salomovs.carrental.exception;

public class VehicleNotFoundException extends RuntimeException {
  public VehicleNotFoundException() {
    super("Vehicle Not Found!");
  }

  public VehicleNotFoundException(String message) {
    super(message);
  }
}
