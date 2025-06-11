package com.salomovs.carrental.dto;

import com.salomovs.carrental.model.entity.Rental;

public record InvoiceDto(
  Rental rental,
  Integer taxes,
  Integer totalAmount
) {}
