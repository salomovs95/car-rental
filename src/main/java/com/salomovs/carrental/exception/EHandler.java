package com.salomovs.carrental.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EHandler {
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Map<String, String>> handle(Exception e) {
    Map<String, String> map = new HashMap<>();
    map.put("error", e.getLocalizedMessage());
    return ResponseEntity.status(400).body(map);
  }
}
