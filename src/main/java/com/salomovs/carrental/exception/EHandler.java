package com.salomovs.carrental.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ValidationException;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EHandler {
  private Logger logger;

  public EHandler() {
    this.logger = LoggerFactory.getLogger(EHandler.class);
  }

  private void logException(Exception e) {
    logger.error(e.getLocalizedMessage());
    e.printStackTrace();
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Map<String, String>> handle(Exception e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());
    
    return ResponseEntity.status(500).body(map);
  }

  @ExceptionHandler({ CustomerNotFoundException.class })
  public ResponseEntity<Map<String, String>> handle(CustomerNotFoundException e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());

    return ResponseEntity.status(404).body(map);
  }

  @ExceptionHandler({ VehicleNotFoundException.class })
  public ResponseEntity<Map<String, String>> handle(VehicleNotFoundException e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());

    return ResponseEntity.status(404).body(map);
  }

  @ExceptionHandler({ RentalNotFoundException.class })
  public ResponseEntity<Map<String, String>> handle(RentalNotFoundException e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());

    return ResponseEntity.status(404).body(map);
  }

  @ExceptionHandler({ InvoiceProcessingException.class })
  public ResponseEntity<Map<String, String>> handle(InvoiceProcessingException e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());

    return ResponseEntity.status(500).body(map);
  }

  @ExceptionHandler({ ValidationException.class })
  public ResponseEntity<Map<String, String>> handle(ValidationException e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());

    return ResponseEntity.status(400).body(map);
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Map<String, String>> handle(ConstraintViolationException e) {
    logException(e);

    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());

    return ResponseEntity.status(400).body(map);
  }
}
