package com.salomovs.carrental.service;

import java.time.LocalDateTime;

import com.salomovs.carrental.db.entity.Rental;
import com.salomovs.carrental.db.entity.Vehicle;
import com.salomovs.carrental.db.repository.Repository;
import com.salomovs.carrental.exception.RentalNotFoundException;
import com.salomovs.carrental.exception.VehicleNotFoundException;

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
    Rental rental = rtRepo.findById(rentalId)
                          .orElseThrow(RentalNotFoundException::new);
    
    double duration = rental.calculateInterval();
    Vehicle vehicle = vhRepo.findById(rental.getVehicleId())
                            .orElseThrow(VehicleNotFoundException::new);
    
    int price = (duration > 12) ? vehicle.getDailyPrice() : vehicle.getHourPrice();
    double amountToPay  = duration * (price/1d) * 100;

    rental.setAmountToPay((int) amountToPay);

    rtRepo.update(rental);
  }
}
