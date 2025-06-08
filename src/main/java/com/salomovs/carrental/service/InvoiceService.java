package com.salomovs.carrental.service;

import com.salomovs.carrental.dto.InvoiceDto;
import com.salomovs.carrental.exception.InvoiceProcessingException;
import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.model.entity.Rental;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.model.repository.CustomerRepository;
import com.salomovs.carrental.model.repository.VehicleRepository;
import com.salomovs.carrental.service.types.TaxService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class InvoiceService {
  private final TaxService taxService;
  private final CustomerRepository ctRepo;
  private final VehicleRepository vhRepo;

  public InvoiceDto processInvoice(Rental rental) {
    if (rental.getReturnAt() == null) throw new InvoiceProcessingException("Can't process invoice! Vehicle not returned!");
    
    Customer customer = ctRepo.findById(rental.getCustomerId()).orElseThrow(()->new RuntimeException("Customer Not Found"));
    Vehicle vehicle = vhRepo.findById(rental.getVehicleId()).orElseThrow(()->new RuntimeException("Vehicle Not Found"));
    
    int taxes = (int) (taxService.processTaxes(rental) * 100);
    int totals = rental.getAmountToPay() + taxes;

    return new InvoiceDto(customer, vehicle, rental, taxes, totals);
  }
}
