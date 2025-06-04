package com.salomovs.carrental.service;

import java.util.List;
import java.util.Optional;

import com.salomovs.carrental.db.entity.Plate;
import com.salomovs.carrental.db.entity.Vehicle;
import com.salomovs.carrental.db.repository.Repository;

public class VehicleService {
  private Repository<Vehicle> vhRepo;

  public VehicleService(Repository<Vehicle> vhRepo) {
    this.vhRepo = vhRepo;
  }

  public Integer registerVehicle(String model,
                                 String brand,
                                 Integer dailyPrice,
                                 Integer hourPrice,
                                 String plateNumber,
                                 String country) {
    Plate plate = new Plate(plateNumber, country);
    Vehicle vehicle = new Vehicle(null, model, brand, dailyPrice, hourPrice, plate);
    return vhRepo.save(vehicle);
  }

  public void updateVehicle(Integer id,
                            Optional<String> model,
                            Optional<String> brand,
                            Optional<String> plateNumber,
                            Optional<String> country,
                            Optional<Integer> dailyPrice,
                            Optional<Integer> hourPrice) {
    Vehicle vehicle = vhRepo.findById(id)
                            .orElseThrow(()->new RuntimeException("Vehicle Not Found"));

    if (model.isPresent()) vehicle.setModel(model.get());
    if (brand.isPresent()) vehicle.setBrand(brand.get());
    if (plateNumber.isPresent()) vehicle.getPlate().setValue(plateNumber.get());
    if (country.isPresent()) vehicle.getPlate().setCountry(country.get());
    if (dailyPrice.isPresent()) vehicle.setDailyPrice(dailyPrice.get());
    if (hourPrice.isPresent()) vehicle.setHourPrice(hourPrice.get());

    vhRepo.update(vehicle);
  }

  public List<Vehicle> listAvailableVehicles() {
    return vhRepo.findAll();
  }
}
