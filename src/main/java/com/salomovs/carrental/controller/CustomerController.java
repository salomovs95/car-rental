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
import com.salomovs.carrental.dto.RegisterCustomerDto;
import com.salomovs.carrental.dto.UpdateCustomerDto;
import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.service.CustomerService;

@RestController @RequestMapping("/customers")
public class CustomerController {
  private final CustomerService customerService;
  private final Logger logger;

  public CustomerController(final CustomerService customerService) {
    this.customerService = customerService;
    this.logger = LoggerFactory.getLogger(CustomerController.class);
  }

  @PostMapping @ApiPostOperation(summary="Register a new Customer")
  public ResponseEntity<Void> registerCustomer(@RequestBody @Valid RegisterCustomerDto body) {
    int customerId = customerService.saveCustomer(body);
    logger.info("New customer created with ID: " + customerId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{customerId}/update") @ApiPutOperation(summary="Update a Customer's info")
  public ResponseEntity<Void> updateCustomer(@RequestParam Integer customerId,
                                             @RequestBody UpdateCustomerDto body) {
    customerService.saveCustomer(customerId, body);
    logger.info("Successfully updated customer with ID: " + customerId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping @ApiGetOperation(summary="Retrieve a list of all Customers")
  public ResponseEntity<List<Customer>> listCustomers() {
    List<Customer> customers = customerService.listCustomers();
    return ResponseEntity.status(HttpStatus.OK).body(customers);
  }

  @GetMapping("/{customerId}") @ApiGetOperation(summary="Retrieve a Customer's info")
  public ResponseEntity<Customer> getCustomerInfo(@RequestParam Integer customerId) {
    Customer customer = customerService.findCustomer(customerId);
    return ResponseEntity.status(HttpStatus.OK).body(customer);
  }
}
