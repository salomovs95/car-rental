package com.salomovs.carrental.db.entity;

public class Vehicle {
  private Integer id;
  private String model;
  private String brand;

  private Integer dailyPrice;
  private Integer hourPrice;

  private Plate plate;

  public Vehicle(Integer id,
                 String model,
                 String brand,
                 Integer dailyPrice,
                 Integer hourPrice,
                 Plate plate) {
    this.id = id;
    this.model = model;
    this.brand = brand;
    this.plate = plate;
    this.dailyPrice = dailyPrice;
    this.hourPrice = hourPrice;
  }

  public Integer getId() { return id; }
  public String getModel() { return model; }
  public String getBrand() { return brand; }
  public Plate getPlate() { return plate; }
  public Integer getDailyPrice() { return dailyPrice; }
  public Integer getHourPrice() { return hourPrice; }

  public void setId(Integer id) { this.id = id; }
  public void setModel(String model) { this.model = model; }
  public void setBrand(String brand) { this.brand = brand; }
  public void setPlate(Plate plate) { this.plate = plate; }
  public void setDailyPrice(Integer dailyPrice) { this.dailyPrice = dailyPrice; }
  public void setHourPrice(Integer hourPrice) { this.hourPrice = hourPrice; }

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
