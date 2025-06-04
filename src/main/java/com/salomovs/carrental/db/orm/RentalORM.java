package com.salomovs.carrental.db.orm;

import java.time.LocalDateTime;

import com.salomovs.carrental.db.entity.Rental;

public class RentalORM implements ORM<Rental> {
  @Override
  public String parse(Rental rental) {
    StringBuilder sb = new StringBuilder();
    return sb.append(rental.getId() + ",")
             .append(rental.getVehicleId() + ",")
             .append(rental.getCustomerId() + ",")
             .append(rental.getRentAt() + ",")
             .append(rental.getReturnAt() + ",")
             .append(rental.getAmountToPay())
             .toString();
  }

  @Override
  public Rental parseString(String rawData) {
    String[] data = rawData.split(",");
    Rental rental = new Rental(Integer.valueOf(data[0]),
                               LocalDateTime.parse(data[3]),
                               LocalDateTime.parse(data[4]),
                               Integer.valueOf(data[1]),
                               Integer.valueOf(data[2]));
    rental.setAmountToPay(Integer.valueOf(data[5]));
    return rental;
  }
}
