package com.salomovs.carrental.service;

import java.time.LocalDateTime;

import com.salomovs.carrental.db.entity.Rental;
import com.salomovs.carrental.db.entity.Vehicle;
import com.salomovs.carrental.db.repository.Repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentalService {
  private Repository<Vehicle> vhRepo;
  private Repository<Rental> rtRepo;
  
  public Integer rentCar(Integer customerId, Integer vehicleId) {
    Rental rental = new Rental(null, LocalDateTime.now(), null, vehicleId, customerId, 0);
    int rentalId = rtRepo.save(rental);
    return rentalId;
  }

  public void returnCar(Integer rentalId) {
    if (rtRepo.findById(rentalId).isEmpty()) throw new RuntimeException("Rental Not Found.");
    Rental rental = rtRepo.findById(rentalId)
                          .orElseThrow(()->new RuntimeException("Rental Not Found"));
    
    double duration = rental.calculateInterval();
    Vehicle vehicle = vhRepo.findById(rental.getVehicleId())
                            .orElseThrow(()->new RuntimeException("Vehicle Not Found"));
    
    int price = (duration > 12) ? vehicle.getDailyPrice() : vehicle.getHourPrice();
    double amountToPay  = duration * (price/1d) * 100;

    rental.setAmountToPay((int) amountToPay);

    rtRepo.update(rental);
  }
}
