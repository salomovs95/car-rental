package com.salomovs.carrental.db.orm;

import com.salomovs.carrental.db.entity.Plate;
import com.salomovs.carrental.db.entity.Vehicle;

public class VehicleORM implements ORM<Vehicle> {
  @Override
  public String parse(Vehicle entity) {
    StringBuilder sb = new StringBuilder();
    return sb.append(entity.getId() + ",")
             .append(entity.getModel() + ",")
             .append(entity.getBrand() + ",")
             .append(entity.getDailyPrice() + ",")
             .append(entity.getHourPrice() + ",")
             .append(entity.getPlate().getValue() + ",")
             .append(entity.getPlate().getCountry())
             .toString();
  }

  @Override
  public Vehicle parseString(String rawData) {
    String[] data = rawData.split(",");
    Plate plate = new Plate(data[5], data[6]);
    return new Vehicle(
      Integer.valueOf(data[0]),
      data[1],
      data[2],
      Integer.valueOf(data[3]),
      Integer.valueOf(data[4]),
      plate
    );
  }
}
