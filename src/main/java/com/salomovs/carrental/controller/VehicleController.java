package com.salomovs.carrental.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salomovs.carrental.config.annotations.ApiGetOperation;
import com.salomovs.carrental.config.annotations.ApiPostOperation;
import com.salomovs.carrental.config.annotations.ApiPutOperation;
import com.salomovs.carrental.dto.RegisterVehicleDto;
import com.salomovs.carrental.dto.UpdateVehicleDto;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.service.VehicleService;

@RestController @RequestMapping("/vehicles")
public class VehicleController {
  private final VehicleService vehicleService;
  private Logger logger;

  public VehicleController(final VehicleService vehicleService) {
    this.vehicleService = vehicleService;
    this.logger = LoggerFactory.getLogger(VehicleController.class);
  }

  @PostMapping @ApiPostOperation(summary="Register a new Vehicle")
  public ResponseEntity<Void> registerVehicle(@RequestBody @Valid RegisterVehicleDto body) {
    Integer vehicleId = vehicleService.registerVehicle(body);
    logger.info("New vehicle created with ID: " + vehicleId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{vehicleId}/update") @ApiPutOperation(summary="Update a Vehicle's info")
  public ResponseEntity<Void> updateVehicleInfo(@RequestParam("vehicleId") Integer vehicleId,
                                                @RequestBody UpdateVehicleDto body) {
    vehicleService.updateVehicle(vehicleId, body);
    logger.info("Successfully updated vehicle with ID: " + vehicleId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping @ApiGetOperation(summary="Retrieve a list of available Vehicles")
  public ResponseEntity<List<Vehicle>> listVehicles() {
    List<Vehicle> vehicles = vehicleService.listAvailableVehicles();
    return ResponseEntity.status(HttpStatus.OK).body(vehicles);
  }
}
