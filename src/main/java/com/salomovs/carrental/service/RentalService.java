package com.salomovs.carrental.service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.salomovs.carrental.exception.RentalNotFoundException;
import com.salomovs.carrental.model.entity.Customer;
import com.salomovs.carrental.model.entity.Rental;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.model.repository.RentalRepository;

@Service @RequiredArgsConstructor
public class RentalService {
  private RentalRepository rtRepo;
  
  public Integer rentVehicle(Customer customer, Vehicle vehicle) {
    Rental rental = new Rental(null, LocalDateTime.now(), null, 0, vehicle, customer);
    int rentalId = rtRepo.save(rental).getId();
    
    return rentalId;
  }

  public void returnVehicle(Integer rentalId) {
    Rental rental = findRental(rentalId);
    
    double duration = rental.calculateInterval();
    Vehicle vehicle = rental.getVehicle();
    
    int price = (duration > 12) ? vehicle.getDailyPrice() : vehicle.getHourPrice();
    double amountToPay  = duration * (price/1d) * 100;

    rental.setAmountToPay((int) amountToPay);

    rtRepo.save(rental);
  }

  public Rental findRental(Integer rentalId) {
    Rental rental = rtRepo.findById(rentalId)
                          .orElseThrow(RentalNotFoundException::new);
    return rental;
  }

  public List<Rental> listRentals() {
    return rtRepo.findAll();
  }
}
