package com.salomovs.carrental.service;

import com.salomovs.carrental.db.entity.Customer;
import com.salomovs.carrental.db.entity.Invoice;
import com.salomovs.carrental.db.entity.Rental;
import com.salomovs.carrental.db.entity.Vehicle;
import com.salomovs.carrental.db.repository.CustomerRepository;
import com.salomovs.carrental.db.repository.RentalRepository;
import com.salomovs.carrental.db.repository.VehicleRepository;
import com.salomovs.carrental.service.types.TaxService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceService {
  private final TaxService taxService;
  private final CustomerRepository ctRepo;
  private final VehicleRepository vhRepo;
  private final RentalRepository rtRepo;

  public Invoice processInvoice(Integer rentalId) {
    Rental rental = rtRepo.findById(rentalId).orElseThrow(()->new RuntimeException("Rental Not Found"));
    if (rental.getReturnAt() == null) throw new RuntimeException("Can't process invoice! Vehicle not returned!");
    
    Customer customer = ctRepo.findById(rental.getCustomerId()).orElseThrow(()->new RuntimeException("Customer Not Found"));
    Vehicle vehicle = vhRepo.findById(rental.getVehicleId()).orElseThrow(()->new RuntimeException("Vehicle Not Found"));
    double taxes = taxService.processTaxes(rental);

    return Invoice.generateInvoice(customer, vehicle, rental, taxes);
  }
}
