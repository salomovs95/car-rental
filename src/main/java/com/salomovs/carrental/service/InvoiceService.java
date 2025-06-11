package com.salomovs.carrental.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.salomovs.carrental.dto.InvoiceDto;
import com.salomovs.carrental.exception.InvoiceProcessingException;
import com.salomovs.carrental.model.entity.Rental;
import com.salomovs.carrental.service.types.TaxService;

@Service @AllArgsConstructor
public class InvoiceService {
  private final TaxService taxService;

  public InvoiceDto processInvoice(Rental rental) {
    if (rental.getReturnAt() == null) throw new InvoiceProcessingException("Can't process invoice! Vehicle not returned!");
    
    int taxes = (int) (taxService.processTaxes(rental) * 100);
    int totals = rental.getAmountToPay() + taxes;

    return new InvoiceDto(rental, taxes, totals);
  }
}
