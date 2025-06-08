package com.salomovs.carrental.dto;

import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.model.entity.Rental;
import com.salomovs.carrental.model.entity.Vehicle;

public record InvoiceDto(
  Customer customer,
  Vehicle vehicle,
  Rental rental,
  Integer taxes,
  Integer totalAmount
) {}
