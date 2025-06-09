package com.salomovs.carrental.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salomovs.carrental.dto.RegisterVehicleDto;
import com.salomovs.carrental.dto.UpdateVehicleDto;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.service.VehicleService;

import jakarta.validation.Valid;

@RestController("/vehicles")
public class VehicleController {
  private final VehicleService vehicleService;
  private Logger logger;

  public VehicleController(final VehicleService vehicleService) {
    this.vehicleService = vehicleService;
    this.logger = LoggerFactory.getLogger(VehicleController.class);
  }

  @PostMapping("")
  public ResponseEntity<Void> registerVehicle(@RequestBody @Valid RegisterVehicleDto body) {
    Integer vehicleId = vehicleService.registerVehicle(body);
    logger.info("New vehicle created with ID: " + vehicleId);
    return ResponseEntity.status(201).build();
  }

  @PutMapping("/{vehicleId}/update")
  public ResponseEntity<Void> updateVehicleInfo(@RequestParam("vehicleId") Integer vehicleId,
                                                @RequestBody UpdateVehicleDto body) {
    vehicleService.updateVehicle(vehicleId, body);
    logger.info("Successfully updated vehicle with ID: " + vehicleId);
    return ResponseEntity.status(200).build();
  }

  public ResponseEntity<List<Vehicle>> listVehicles() {
    List<Vehicle> vehicles = vehicleService.listAvailableVehicles();
    return ResponseEntity.status(200).body(vehicles);
  }
}
