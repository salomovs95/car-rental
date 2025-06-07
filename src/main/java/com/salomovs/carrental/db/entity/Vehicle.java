package com.salomovs.carrental.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Vehicle {
  private Integer id;
  private String model;
  private String brand;

  private Integer dailyPrice;
  private Integer hourPrice;

  private Plate plate;

  @Override
  public String toString() {
    String strVehicle = """
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ::  VEHICLE  :::::::::::::::::::::::::::::::::  ID: %d  ::
    ::  Model: %s  :::::  Brand: %s  :::::  Plate: %s (%s)  ::
    ::  Prices  :::::::::  Daily: %.2f  ::::::::  Hourly: %.2f  ::
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    """;
    return String.format(strVehicle,
                         id, 
                         model,
                         brand,
                         plate.getValue(),
                         plate.getCountry(),
                         dailyPrice/100d,
                         hourPrice/100d);
  }
}
