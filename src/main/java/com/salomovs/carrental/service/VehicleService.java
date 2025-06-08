package com.salomovs.carrental.service;

import java.util.List;
import java.util.Optional;

import com.salomovs.carrental.model.entity.Plate;
import com.salomovs.carrental.model.entity.Vehicle;
import com.salomovs.carrental.model.repository.VehicleRepository;
import com.salomovs.carrental.exception.VehicleNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehicleService {
  private VehicleRepository vhRepo;

  public Integer registerVehicle(String model,
                                 String brand,
                                 Integer dailyPrice,
                                 Integer hourPrice,
                                 String plateNumber,
                                 String country) {
    Plate plate = new Plate(plateNumber, country);
    Vehicle vehicle = new Vehicle(null, model, brand, dailyPrice, hourPrice, plate);
    return vhRepo.save(vehicle).getId();
  }

  public void updateVehicle(Integer id,
                            Optional<String> model,
                            Optional<String> brand,
                            Optional<String> plateNumber,
                            Optional<String> country,
                            Optional<Integer> dailyPrice,
                            Optional<Integer> hourPrice) {
    Vehicle vehicle = vhRepo.findById(id)
                            .orElseThrow(VehicleNotFoundException::new);

    if (model.isPresent()) vehicle.setModel(model.get());
    if (brand.isPresent()) vehicle.setBrand(brand.get());
    if (plateNumber.isPresent()) vehicle.getPlate().setValue(plateNumber.get());
    if (country.isPresent()) vehicle.getPlate().setCountry(country.get());
    if (dailyPrice.isPresent()) vehicle.setDailyPrice(dailyPrice.get());
    if (hourPrice.isPresent()) vehicle.setHourPrice(hourPrice.get());

    vhRepo.save(vehicle);
  }

  public List<Vehicle> listAvailableVehicles() {
    return vhRepo.findAll();
  }
}
