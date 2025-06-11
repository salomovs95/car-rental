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

import com.salomovs.carrental.dto.InvoiceDto;
import com.salomovs.carrental.dto.VehicleRentalDto;
import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.model.entity.Rental;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.service.CustomerService;
import com.salomovs.carrental.service.InvoiceService;
import com.salomovs.carrental.service.RentalService;
import com.salomovs.carrental.service.VehicleService;

@RestController @RequestMapping("/rentals")
public class RentalController {
  private final Logger logger;
  private final CustomerService customerService;
  private final RentalService rentalService;
  private final VehicleService vehicleService;
  private final InvoiceService invoiceService;

  public RentalController(final CustomerService customerService,
                          final VehicleService vehicleService,
                          final RentalService rentalService,
                          final InvoiceService invoiceService) {
    this.logger = LoggerFactory.getLogger(RentalController.class);
    this.customerService = customerService;
    this.vehicleService = vehicleService;
    this.rentalService = rentalService;
    this.invoiceService = invoiceService;
  }

  @PostMapping
  public ResponseEntity<Void> handleRentalment(@RequestBody @Valid VehicleRentalDto body) {
    Customer customer = customerService.findCustomer(body.customerId());
    Vehicle vehicle = vehicleService.findVehicle(body.vehicleId());
    Integer rentalId = rentalService.rentVehicle(customer, vehicle);
    logger.info("Successfully rented vehicle of ID " + body.vehicleId() + " to customer of ID " + body.customerId() + ", operation ID: " + rentalId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


  @PutMapping("/{rentalId}")
  public ResponseEntity<Void> rentalReturn(@RequestParam("rentalId") Integer rentalId) {
    rentalService.returnVehicle(rentalId);
    logger.info("Vehicle successfully returned for rental of ID: " + rentalId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping
  public ResponseEntity<List<Rental>> listRentals() {
    List<Rental> rentals = rentalService.listRentals();
    return ResponseEntity.status(HttpStatus.OK).body(rentals);
  }

  @GetMapping("/{rentalId}")
  public ResponseEntity<Rental> findRental(@RequestParam("rentalId") Integer rentalId) {
    Rental rental = rentalService.findRental(rentalId);
    return ResponseEntity.status(HttpStatus.OK).body(rental);
  }

  @GetMapping("/{rentalId}/invoice")
  public ResponseEntity<InvoiceDto> getInvoice(@RequestParam("rentalId") Integer rentalId) {
    Rental rental = rentalService.findRental(rentalId);
    InvoiceDto invoice = invoiceService.processInvoice(rental);
    return ResponseEntity.status(HttpStatus.OK).body(invoice);
  }
}
