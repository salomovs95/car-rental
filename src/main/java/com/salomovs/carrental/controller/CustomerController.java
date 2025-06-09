package com.salomovs.carrental.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salomovs.carrental.dto.RegisterCustomerDto;
import com.salomovs.carrental.dto.UpdateCustomerDto;
import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.service.CustomerService;

@RestController("/customers")
public class CustomerController {
  private final CustomerService customerService;
  private final Logger logger;

  public CustomerController(final CustomerService customerService) {
    this.customerService = customerService;
    this.logger = LoggerFactory.getLogger(CustomerController.class);
  }

  @PostMapping("")
  public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerDto body) {
    int customerId = customerService.saveCustomer(body);
    logger.info("New customer created with ID: " + customerId);
    return ResponseEntity.status(201).build();
  }

  @PutMapping("/{customerId}/update")
  public ResponseEntity<Void> updateCustomer(@RequestParam Integer customerId,
                                             @RequestBody UpdateCustomerDto body) {
    customerService.saveCustomer(customerId, body);
    logger.info("Successfully updated customer with ID: " + customerId);
    return ResponseEntity.status(200).build();
  }

  @GetMapping("")
  public ResponseEntity<List<Customer>> listCustomers() {
    List<Customer> customers = customerService.listCustomers();
    return ResponseEntity.status(200).body(customers);
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<Customer> getCustomerInfo(@RequestParam Integer customerId) {
    Customer customer = customerService.findCustomer(customerId);
    return ResponseEntity.status(200).body(customer);
  }
}
